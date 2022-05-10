package kr.hs.dgsw.cns.schoolmealbacksetup.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 예외 발생 시, 반환하게 되는 DTO 클래스입니다.
 * @implSpec DTO 추가를 해야할 경우, 해당 클래스를 상속하여 필드 추가할 것
 */
@Getter
@Builder
@AllArgsConstructor
public class ExceptionDto {

    @JsonProperty(value = "timestamp", index = 0)   // 직렬화 과정에서 우선순위가 밀려나는 것을 방지하기 위함
    private final LocalDateTime timeStamp = LocalDateTime.now();

    private final String message;
}
