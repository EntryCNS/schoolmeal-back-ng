package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {

}
