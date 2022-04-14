package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReviewDto {

    String message;
    short rate;

}
