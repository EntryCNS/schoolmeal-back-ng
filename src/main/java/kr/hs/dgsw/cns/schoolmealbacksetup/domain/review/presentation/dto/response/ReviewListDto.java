package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ReviewListDto {

    private final Integer page;

    @JsonProperty("page_count")
    private final Integer pageCount;

    private final List<ReviewDto> result;

}
