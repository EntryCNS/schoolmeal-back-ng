package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
