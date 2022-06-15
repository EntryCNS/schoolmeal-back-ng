package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.entity.Review;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByDateAndReviewTime(LocalDate date, @NotNull ReviewTime reviewTime, Pageable pageable);
}
