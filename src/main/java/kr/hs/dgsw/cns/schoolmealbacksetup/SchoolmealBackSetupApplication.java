package kr.hs.dgsw.cns.schoolmealbacksetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchoolmealBackSetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolmealBackSetupApplication.class, args);
    }

}
