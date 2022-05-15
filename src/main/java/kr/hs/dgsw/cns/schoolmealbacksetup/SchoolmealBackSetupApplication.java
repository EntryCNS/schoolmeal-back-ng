package kr.hs.dgsw.cns.schoolmealbacksetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SchoolmealBackSetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolmealBackSetupApplication.class, args);
    }

}
