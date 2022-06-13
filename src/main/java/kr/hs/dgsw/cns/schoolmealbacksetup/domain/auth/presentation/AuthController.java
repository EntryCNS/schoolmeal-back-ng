package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.TokenRefreshRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.OAuthResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.TokenRefreshResponse;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.aop.Authorized;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController {
    private final AuthService authService;

    @GetMapping("/oauth")
    OAuthResponseDto oAuth(@RequestParam String code) {
        return authService.oAuthGoogle(code);
    }

    @GetMapping("/refresh")
    @Authorized
    TokenRefreshResponse refresh(@RequestBody @Valid TokenRefreshRequest request, Authentication authentication) {
        return authService.refreshToken((User)authentication, request);
    }
}