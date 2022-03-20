package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
