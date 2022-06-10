package kr.hs.dgsw.cns.schoolmealbacksetup.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
