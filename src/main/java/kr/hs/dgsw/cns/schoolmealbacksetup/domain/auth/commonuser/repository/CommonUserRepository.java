package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.commonuser.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.commonuser.entity.CommonUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonUserRepository extends CrudRepository<CommonUser, Long> {

    @Query("select * from commonuser where email = ? limit 1")
    boolean existByEmail(String email);


}
