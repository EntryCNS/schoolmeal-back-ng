package kr.hs.dgsw.cns.schoolmealbacksetup.global.security;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfiguration {
    private Long tokenPeriod;
    private String secretKey;
}
