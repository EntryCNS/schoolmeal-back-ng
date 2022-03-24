package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.commonuser.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.commonuser.entity.CommonUser;
import org.springframework.data.repository.CrudRepository;

public interface CommonUserRepository extends CrudRepository<CommonUser, Long> {

}
