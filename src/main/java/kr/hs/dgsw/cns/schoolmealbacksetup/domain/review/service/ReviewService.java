package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;

public interface ReviewService {

    WriteReviewResponseDto writeReview(User user, WriteReviewRequestDto writeReviewRequest);

    ReviewListDto getReviewByDate(String date, ReviewTime reviewTime, int page);

}
