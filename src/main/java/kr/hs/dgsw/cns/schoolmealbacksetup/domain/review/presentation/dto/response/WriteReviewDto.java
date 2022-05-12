package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WriteReviewDto {

    String date;

    ReviewTime reviewTime;

    String message;

    int rate;
}
