package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.*;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.*;

public interface AuthService {

    public SignInResponse signIn(SignInRequest signinRequest);

    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);

    public OAuthResponseDto oAuthGoogle(String code);

}
