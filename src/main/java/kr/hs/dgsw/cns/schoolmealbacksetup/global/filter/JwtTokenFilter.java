package kr.hs.dgsw.cns.schoolmealbacksetup.global.filter;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtProvider;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String bearerToken = jwtProvider.getTokenFromHeader(request.getHeader("Authorization"));
            if (bearerToken != null) {
                Authentication authentication = jwtProvider.getAuthenticationFromToken(bearerToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            filterException(e, response);
        }
    }

    private void filterException(TokenException exception, HttpServletResponse response)
            throws IOException {
        response.setStatus(exception.getStatus().value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(exception.getMessage());
    }
}
