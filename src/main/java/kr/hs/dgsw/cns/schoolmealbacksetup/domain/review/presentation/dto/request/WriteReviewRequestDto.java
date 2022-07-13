package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class WriteReviewRequestDto {

    @NotNull
    private String date;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ReviewTime reviewTime;

    @NotNull
    private String message;

    @NotNull
    @Max(5)
    @Min(1)
    private int rate;


}
