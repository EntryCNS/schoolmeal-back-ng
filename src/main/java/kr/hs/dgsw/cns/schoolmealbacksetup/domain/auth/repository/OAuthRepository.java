package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.entity.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OAuthRepository extends CrudRepository<OAuthUser, Long> {

}
