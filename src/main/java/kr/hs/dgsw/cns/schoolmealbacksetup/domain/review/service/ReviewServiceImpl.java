package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.entity.Review;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.repository.ReviewRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public WriteReviewResponseDto writeReview(User author, WriteReviewRequestDto writeReviewRequest) {

        Review review = Review.builder()
                .message(writeReviewRequest.getMessage())
                .user(author)
                .date(LocalDate.now())
                .rate(writeReviewRequest.getRate())
                .reviewTime(writeReviewRequest.getReviewTime())
                .build();
        reviewRepository.save(review);

        return new WriteReviewResponseDto(
                writeReviewRequest.getDate(),
                writeReviewRequest.getReviewTime(),
                writeReviewRequest.getMessage(),
                writeReviewRequest.getRate()
        );
    }

    @Override
    public ReviewListDto getReviewByDate(String date, ReviewTime reviewTime, int page) {
        PageRequest reviewPageRequest = PageRequest.of(page , 10);

        Page<Review> reviewPage = reviewRepository.findAllByDateAndReviewTime(LocalDate.parse(String.format(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))), reviewTime, reviewPageRequest);
        List<ReviewDto> responseList = reviewPage.getContent()
                .stream().map((it) -> ReviewDto.builder()
                        .message(it.getMessage())
                        .rate((short) it.getRate())
                        .build())
                .collect(Collectors.toList());

        return new ReviewListDto(page, reviewPage.getTotalPages(), responseList);
    }
}