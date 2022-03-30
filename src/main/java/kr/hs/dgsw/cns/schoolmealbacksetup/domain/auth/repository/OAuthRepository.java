package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.entity.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthRepository extends JpaRepository<OAuthUser, Long> {

}
