package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;


public interface ReviewService {
    WriteReviewRequestDto writeReview(WriteReviewDto writeReview);

    ReviewListDto getReviewByDate(String date, ReviewTime reviewTime);
}
