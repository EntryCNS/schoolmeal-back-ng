package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation;


import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service.ReviewService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ReviewListDto getReviewList(@RequestParam String date,@RequestParam ReviewTime reviewTime, int page){
        return reviewService.getReviewByDate(date,reviewTime,page);
    }

    @PostMapping
    public WriteReviewResponseDto writeReview(Authentication authenticator, @RequestBody WriteReviewRequestDto writeReviewRequestDto) {
        return reviewService.writeReview(authenticator,writeReviewRequestDto);
    }
}
