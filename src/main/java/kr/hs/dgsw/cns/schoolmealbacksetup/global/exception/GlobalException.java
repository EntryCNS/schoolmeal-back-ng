package kr.hs.dgsw.cns.schoolmealbacksetup.global.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GlobalException extends RuntimeException {
    @JsonProperty(value = "time_stamp")
    private final LocalDateTime timeStamp = LocalDateTime.now();
}
