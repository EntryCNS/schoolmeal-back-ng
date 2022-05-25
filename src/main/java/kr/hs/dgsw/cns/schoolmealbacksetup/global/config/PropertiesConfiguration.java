package kr.hs.dgsw.cns.schoolmealbacksetup.global.config;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {
    @Bean
    public JwtConfiguration jwtConfiguration() {
        return new JwtConfiguration();
    }
}
