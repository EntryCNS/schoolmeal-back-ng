package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.*;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.*;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;

public interface AuthService {
    public TokenRefreshResponse refreshToken(User user, TokenRefreshRequest tokenRefreshRequest);

    public OAuthResponseDto oAuthGoogle(String code);

}
