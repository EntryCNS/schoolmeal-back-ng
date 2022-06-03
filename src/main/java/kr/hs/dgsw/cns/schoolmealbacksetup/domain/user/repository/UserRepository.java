package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByOpenId(@NotNull String openId);
}
