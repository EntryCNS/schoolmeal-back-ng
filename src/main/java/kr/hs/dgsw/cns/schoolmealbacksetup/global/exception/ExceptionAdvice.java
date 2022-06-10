package kr.hs.dgsw.cns.schoolmealbacksetup.global.exception;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    protected ResponseEntity<ExceptionDto> undefinedException(Exception e) {
        final ExceptionDto exceptionDto = ExceptionDto.builder()
                .message("서버에서 오류가 발생하였습니다.")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDto);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionDto> definedException(BusinessException e) {
        final ExceptionDto exceptionDto = ExceptionDto.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getStatus())
                .body(exceptionDto);
    }

    @ExceptionHandler(MenuRequest.CannotFound.class)
    protected ResponseEntity<ExceptionDto> menuCannotFoundException(MenuRequest.CannotFound cannotFound) {
        return sendException(cannotFound);
    }

    @ExceptionHandler(MenuRequest.PageCannotNegative.class)
    protected ResponseEntity<ExceptionDto> menuPageCannotNegativeException(
            MenuRequest.PageCannotNegative pageCannotNegative
    ) {
        return sendException(pageCannotNegative, "Parameter 'page' cannot be negative.");
    }

    /**
     *
     * @param runtimeException 예외 처리할 예외 클래스의 인스턴스를 매개변수로 받습니다.
     * @apiNote 매개변수는 반드시 {@code RuntimeException}의 인스턴스이어야합니다.
     * @return 예외 response. {@code ResponseEntity<ExceptionDto>}를 반환합니다.
     */
    private ResponseEntity<ExceptionDto> sendException(RuntimeException runtimeException) {
        return sendException(runtimeException, runtimeException.getMessage());
    }

    private ResponseEntity<ExceptionDto> sendException(
            RuntimeException runtimeException, String message
    ) {
        return ResponseEntity.status(getHttpStatus(runtimeException.getClass()))
                .body(new ExceptionDto(message));
    }

    /**
     *
     * @param exceptionClass 예외 처리할 클래스를 매개변수로 받습니다.
     * @apiNote 반드시 {@code RuntimeException}을 상속받은 클래스만을 받습니다.
     * @return {@code @ResponseStatus}어노테이션에 있는 {@code value}를 가져와 반환합니다.
     */
    private HttpStatus getHttpStatus(Class<? extends RuntimeException> exceptionClass) {
        return exceptionClass
                .getDeclaredAnnotation(ResponseStatus.class)
                .value();
    }
}
