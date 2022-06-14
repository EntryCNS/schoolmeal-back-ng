package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.VoteId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, VoteId> {

    @Query("select (count(v) > 0) from Vote v where v.id.id.user = ?1")
    boolean existsById_Id_User(User user);

    @Query("select v from Vote v where v.id.id.user = ?1 and v.menuRequest = ?2")
    Optional<Vote> findById_Id_User_AndMenuRequest(User user, MenuRequest menuRequest);

}
