package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@Builder
public class WriteReviewResponseDto {

    private String date;

    @JsonProperty("review_time")
    @Enumerated(value = EnumType.STRING)
    private ReviewTime reviewTime;

    private String message;

    private int rate;
}
