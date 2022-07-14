package kr.hs.dgsw.cns.schoolmealbacksetup.service.review;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.request.WriteReviewRequestDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.ReviewListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.presentation.dto.response.WriteReviewResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.service.ReviewServiceImpl;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    ReviewServiceImpl reviewService;

    private User newUser = User.builder()
            .name("이승민")
            .role(UserRole.USER)
            .id(0L)
            .build();

    @Test
    void writeReview() {
        WriteReviewRequestDto writeReviewRequestDto = WriteReviewRequestDto.builder()
                .reviewTime(ReviewTime.BREAKFAST)
                .date(String.format("YYYY-MM-DD",LocalDate.now()))
                .message("맛없어요")
                .rate(5)
                .build();

        WriteReviewResponseDto expected = WriteReviewResponseDto.builder()
                .reviewTime(ReviewTime.BREAKFAST)
                .rate(5)
                .message("맛없어요")
                .date(String.format("YYYY-MM-DD",LocalDate.now()))
                .build();

        when(reviewService.writeReview(newUser, writeReviewRequestDto)).thenReturn(expected);
        assertThat(reviewService.writeReview(newUser, writeReviewRequestDto)).isEqualTo(expected);
    }

    @Test
    void getReviewByDate() {
        String date = "2022-07-13";
        ReviewTime time = ReviewTime.LUNCH;
        int page = 0;

        ReviewListDto expected = new ReviewListDto(
                0, 10, List.of(
                        new ReviewDto("맛만 좋으면 그만이지~", (short)5),
                        new ReviewDto("맛만 좋으면 그만이지이~", (short)4),
                        new ReviewDto("맛만 좋으면 그만이지이이~", (short)3),
                        new ReviewDto("맛만 좋으면 그만이지이이이~", (short)2),
                        new ReviewDto("맛만 좋으면 그만이지이이이이~", (short)1),
                        new ReviewDto("맛만 좋으면 그만이지이이이이이~", (short)2),
                        new ReviewDto("맛만 좋으면 그만이지이이이이이이잉 앗살라말라이쿰~", (short)3)
            )
        );

        when(reviewService.getReviewByDate(date, time, page)).thenReturn(expected);
        ReviewListDto actual = reviewService.getReviewByDate(date, time, page);

        assertThat(expected).isEqualTo(actual);
    }

}
