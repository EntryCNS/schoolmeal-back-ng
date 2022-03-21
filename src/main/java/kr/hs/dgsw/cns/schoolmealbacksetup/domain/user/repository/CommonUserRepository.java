package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.CommonUser;
import org.springframework.data.repository.CrudRepository;

public interface CommonUserRepository extends CrudRepository<CommonUser, Long> {

}
