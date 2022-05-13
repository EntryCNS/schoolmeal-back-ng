package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.SignInRequest
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request.TokenRefreshRequest
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.OAuthResponseDto
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.SignInResponse
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response.TokenRefreshResponse
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping
    fun signin(@RequestBody request: SignInRequest): SignInResponse {
        return authService.signIn(request);
    }

    @GetMapping("/oauth")
    fun oAuth(@RequestParam code: String): OAuthResponseDto {
        return authService.oAuthGoogle(code)
    }

    fun refresh(@RequestBody request: TokenRefreshRequest): TokenRefreshResponse {
        return authService.refreshToken(request)
    }

}