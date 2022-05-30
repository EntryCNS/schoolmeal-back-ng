package kr.hs.dgsw.cns.schoolmealbacksetup.config;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PropertiesTest {
    @Autowired
    private JwtConfiguration jwtConfiguration;
    private static final Logger logger = LoggerFactory.getLogger(PropertiesTest.class);

    @DisplayName("Jwt configuration")
    @Test
    void printConfiguration() {
        logger.info(jwtConfiguration.toString());
        assertThat(jwtConfiguration).isNotNull();
    }
}
