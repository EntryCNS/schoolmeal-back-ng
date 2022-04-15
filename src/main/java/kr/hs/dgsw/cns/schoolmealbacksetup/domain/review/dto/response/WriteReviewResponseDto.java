package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class WriteReviewResponseDto {

    @NotNull
    private String date;

    @NotNull
    private ReviewTime reviewTime;

    @NotNull
    private String massage;

    @NotNull
    @Max(5)
    @Min(1)
    private int rate;
}
