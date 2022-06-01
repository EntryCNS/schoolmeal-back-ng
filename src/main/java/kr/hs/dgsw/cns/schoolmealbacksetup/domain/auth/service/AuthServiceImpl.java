package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.TokenRefreshRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.OAuthResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.TokenRefreshResponse;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.config.GoogleAuthConfiguration;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.google.GoogleApiService;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.google.GoogleAuthService;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Response;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final GoogleAuthService googleAuthService;
    private final GoogleApiService googleApiService;
    private final GoogleAuthConfiguration googleAuthConfiguration;

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    @Override
    public TokenRefreshResponse refreshToken(User user, TokenRefreshRequest tokenRefreshRequest) {
        return new TokenRefreshResponse(
                jwtProvider.generateAccessToken(user.getUsername())
        );
    }

    @Override
    @Transactional
    public OAuthResponseDto oAuthGoogle(String code) {
        GoogleAuthService.GetAccessTokenResponse accessTokenResponse = getAccessTokenByCode(code);
        logger.info(accessTokenResponse.toString());
        GoogleApiService.GetUserInfoResponse userInfo = getUserInfoByAccessToken(accessTokenResponse.getAccessToken());
        logger.info(userInfo.toString());

        if(!"dgsw.hs.kr".equals(userInfo.getEmail().split("@")[1]))
            throw new User.InvalidSchoolException();

        User user = userRepository.findByOpenId(userInfo.getOpenId())
                .orElseGet(() -> joinOAuthUser(userInfo));

        return new OAuthResponseDto(
                jwtProvider.generateRefreshToken(user.getUsername())
        );
    }

    private GoogleAuthService.GetAccessTokenResponse getAccessTokenByCode(String code) {
        try {
            Response<GoogleAuthService.GetAccessTokenResponse> tokenRes = googleAuthService.getAccessToken(
                            code, googleAuthConfiguration.getClientId(), googleAuthConfiguration.getClientSecret(),
                            "authorization_code", googleAuthConfiguration.getClientRedirect())
                    .execute();
            if(!tokenRes.isSuccessful()) throw new User.FailedToAuthException();
            return tokenRes.body();
        }catch (IOException ex) {
            ex.printStackTrace();
            throw new User.FailedToAuthException();
        }
    }

    private GoogleApiService.GetUserInfoResponse getUserInfoByAccessToken(String accessToken) {
        try {
            Response<GoogleApiService.GetUserInfoResponse> infoRes = googleApiService
                    .getInfo(String.format("Bearer %s", accessToken))
                    .execute();
            if(!infoRes.isSuccessful()) {
                logger.error(new String(infoRes.errorBody().bytes()));
                throw new User.FailedToAuthException();
            }
            return infoRes.body();
        }catch (IOException ex) {
            ex.printStackTrace();
            throw new User.FailedToAuthException();
        }
    }

    @Transactional
    public User joinOAuthUser(GoogleApiService.GetUserInfoResponse userInfo) {
        User newUser = User.builder()
                .name(userInfo.getGivenName())
                .role(UserRole.USER)
                .openId(userInfo.getOpenId())
                .build();

        return userRepository.save(newUser);
    }
}
