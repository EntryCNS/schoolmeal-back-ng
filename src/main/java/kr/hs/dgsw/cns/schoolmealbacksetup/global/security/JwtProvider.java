package kr.hs.dgsw.cns.schoolmealbacksetup.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String IDENT_ACCESS = "ACCESS";
    private static final String IDENT_REFRESH = "REFRESH";

    private final JwtConfiguration jwtConfig;

    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String loginId) {
        return generateToken(IDENT_ACCESS, loginId, jwtConfig.getTokenPeriod());
    }

    public String generateRefreshToken(String loginId) {
        return generateToken(IDENT_REFRESH, loginId, jwtConfig.getTokenPeriod());
    }

    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token).getBody();
    }

    public String extractLoginIdFromToken(String token) {
        return parseToken(token).getSubject();
    }

    public String getTokenType(String token) {
        return parseToken(token).get("type", String.class);
    }

    private String generateToken(String type, String loginId, Long expWtihSecond) {
        final Date tokenCreationDate = new Date();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .setSubject(loginId)
                .claim("type", type)
                .setIssuedAt(tokenCreationDate)
                .setIssuedAt(new Date(tokenCreationDate.getTime() + expWtihSecond * 1000))
                .compact();
    }

    public String getTokenFromHeader(String headerValue) {
        if (headerValue != null && headerValue.startsWith("Bearer "))
            return headerValue.replace("Bearer ", "");
        return null;
    }

    public Authentication getAuthenticationFromToken(String token) {
        String extractToken = extractLoginIdFromToken(token);
        UserDetails userDetails = authDetailsService.loadUserByUsername(extractToken);
        return new UsernamePasswordAuthenticationToken(userDetails, "", null);
    }
}
