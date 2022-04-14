package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class WriteReviewResponseDto {

    @NotNull
    private String date;

    @NotNull
    private String massage;

    @NotNull
    @Size(max = 5)
    private int rate;
}
