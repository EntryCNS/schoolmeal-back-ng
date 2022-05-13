package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WriteReviewResponseDto {
    @JsonProperty("author_id")
    private String authorId;

    private String date;

    @JsonProperty("review_time")
    private ReviewTime reviewTime;

    private String message;

    private int rate;
}
