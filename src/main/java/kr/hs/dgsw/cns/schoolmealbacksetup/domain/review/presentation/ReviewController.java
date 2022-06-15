package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation;


import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service.ReviewService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ReviewListDto getReviewList(LocalDate date, ReviewTime reviewTime, int page){
        return reviewService.getReviewByDate(date,reviewTime,page);
    }

    @GetMapping
    public WriteReviewResponseDto writeReview(User user, WriteReviewRequestDto writeReviewRequestDto) {
        return reviewService.writeReview(user,writeReviewRequestDto);
    }
}
