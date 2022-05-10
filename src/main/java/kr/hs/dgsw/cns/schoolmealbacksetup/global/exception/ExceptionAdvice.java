package kr.hs.dgsw.cns.schoolmealbacksetup.global.exception;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외 지연을 가로채 중간 기능을 수행하는 Advice 클래스입니다.
 * @apiNote 매개변수를 요구하는 경우, 해당 매개변수의 필드를 가져와 처리합니다.
 * @implSpec 특정 throwable 예외 클래스에 특정한 예외를 발생시켜야하는 경우, defaultException 메소드를 참고해서 추가해주세요.
 * <pre>
 *     {@code
 *     @ExceptionHandler(Exception.class)
 *     protected ResponseEntity<ExceptionDto> defaultException(Exception e) {
 *         final ExceptionDto exceptionDto = ExceptionDto.builder()
 *                 .message("서버에서 오류가 발생하였습니다.")
 *                 .build();
 *         return ResponseEntity
 *              .status(HttpStatus.INTERNAL_SERVER_ERROR)
 *              .body(exceptionDto);
 *     }
 *     }
 * </pre>
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> defaultException(Exception e) {
        final ExceptionDto exceptionDto = ExceptionDto.builder()
                .message("서버에서 오류가 발생하였습니다.")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDto);
    }
}
