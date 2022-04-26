package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
