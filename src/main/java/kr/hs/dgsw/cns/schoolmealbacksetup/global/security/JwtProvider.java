package kr.hs.dgsw.cns.schoolmealbacksetup.global.security;

import io.jsonwebtoken.*;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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
        try {
            return parseToken(token).getSubject();
        } catch (SignatureException | MalformedJwtException e) {
            throw new TokenException("잘못된 Jwt 서명입니다");
        } catch (ExpiredJwtException e) {
            throw new TokenException("만료된 토큰입니다");
        } catch (IllegalArgumentException | UnsupportedJwtException e) {
            throw new TokenException("비정상적인 토큰입니다");
        }
    }

    public String getTokenType(String token) {
        return parseToken(token).get("type", String.class);
    }

    private String generateToken(String type, String loginId, Long expWithSecond) {
        final Date tokenCreationDate = new Date();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .setSubject(loginId)
                .claim("type", type)
                .setIssuedAt(tokenCreationDate)
                .setIssuedAt(new Date(tokenCreationDate.getTime() + expWithSecond * 1000))
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
