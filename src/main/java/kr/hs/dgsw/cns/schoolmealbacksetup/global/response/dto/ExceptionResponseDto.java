package kr.hs.dgsw.cns.schoolmealbacksetup.global.response.dto;

import lombok.Builder;
import lombok.Setter;

import java.util.Date;

@Setter
@Builder
public class ExceptionResponseDto {

    private final Date timeStamp;
    private final String message;

}
