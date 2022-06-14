package kr.hs.dgsw.cns.schoolmealbacksetup.config;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(JwtConfiguration.class)
@TestPropertySource("classpath:application.properties")
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
