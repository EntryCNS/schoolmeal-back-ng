package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.TokenRefreshRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.OAuthResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.TokenRefreshResponse;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Resource(name = "AuthServiceImpl")
    private final AuthService authService;

    @GetMapping("/oauth")
    public OAuthResponseDto oAuth(@RequestParam String code) {
        return authService.oAuthGoogle(code);
    }

    @GetMapping("/refresh")
    public TokenRefreshResponse refresh(@RequestBody @Valid TokenRefreshRequest request, Principal principal) {
        if(!(principal instanceof User)) throw new User.UnauthorizedException();
        return authService.refreshToken((User)principal, request);
    }
}
