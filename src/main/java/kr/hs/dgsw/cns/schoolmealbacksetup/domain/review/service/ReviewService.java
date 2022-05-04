package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;

public interface ReviewService {
    WriteReviewRequestDto writeReview(ReviewDto reviewDto);

    ReviewListDto checkReview(Integer pageCount);
}
