package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;


public interface ReviewService {

    WriteReviewResponseDto writeReview(Authentication authenticator, WriteReviewRequestDto writeReviewRequest);

    ReviewListDto getReviewByDate(String date, ReviewTime reviewTime, int page);

}
