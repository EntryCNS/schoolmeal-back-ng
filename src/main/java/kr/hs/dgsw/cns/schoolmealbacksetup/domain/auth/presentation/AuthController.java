package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.SignInRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.TokenRefreshRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.OAuthResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.SignInResponse;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.TokenRefreshResponse;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController {
    private final AuthService authService;

    @PostMapping
    SignInResponse signin(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

    @GetMapping("/oauth")
    OAuthResponseDto oAuth(@RequestParam String code) {
        return authService.oAuthGoogle(code);
    }

    TokenRefreshResponse refresh(@RequestBody TokenRefreshRequest request) {
        return authService.refreshToken(request);
    }

}