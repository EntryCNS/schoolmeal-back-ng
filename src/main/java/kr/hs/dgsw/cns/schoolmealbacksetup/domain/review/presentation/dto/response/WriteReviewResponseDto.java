package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class WriteReviewResponseDto {

    private LocalDate date;

    @JsonProperty("review_time")
    private ReviewTime reviewTime;

    private String message;

    private int rate;
}
