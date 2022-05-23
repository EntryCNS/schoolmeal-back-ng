package kr.hs.dgsw.cns.schoolmealbacksetup.global.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Getter
@ConstructorBinding
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfiguration {
    private Long tokenPeriod;
    private String secretKey;
}
