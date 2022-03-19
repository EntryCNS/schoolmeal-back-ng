package kr.hs.dgsw.cns.schoolmealbacksetup.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/api/error")
@RestControllerAdvice
public class ExceptionController {

    @RequestMapping
    public void throwException(HttpServletRequest request) {
        throw new HttpClientErrorException(
                HttpStatus.valueOf((int) request.getAttribute("code")),
                (String) request.getAttribute("msg")
        );
    }

}
