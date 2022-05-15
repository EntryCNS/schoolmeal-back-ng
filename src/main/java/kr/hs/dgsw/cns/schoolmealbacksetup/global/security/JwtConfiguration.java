package kr.hs.dgsw.cns.schoolmealbacksetup.global.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtConfiguration {
    private final Long tokenPeriod;
    private final String secretKey;
}
