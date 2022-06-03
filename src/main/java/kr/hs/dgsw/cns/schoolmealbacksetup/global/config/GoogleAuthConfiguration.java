package kr.hs.dgsw.cns.schoolmealbacksetup.global.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google.oauth")
public class GoogleAuthConfiguration {
    private String clientId;
    private String clientSecret;
    private String clientRedirect;
}
