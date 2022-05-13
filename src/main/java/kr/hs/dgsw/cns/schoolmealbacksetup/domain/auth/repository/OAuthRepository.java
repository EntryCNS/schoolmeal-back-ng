package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.oauthuser.entity.OAuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRepository extends CrudRepository<OAuthUser, Long> {

}
