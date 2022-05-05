package kr.hs.dgsw.cns.schoolmealbacksetup.global.exception;

import java.time.LocalDateTime;

public class ExceptionTime {

    private ExceptionTime() {}

    public static LocalDateTime getTime() {
        return LocalDateTime.now();
    }

}
