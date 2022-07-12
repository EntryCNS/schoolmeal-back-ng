package kr.hs.dgsw.cns.schoolmealbacksetup.global.security;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenException extends BusinessException {

    public TokenException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
