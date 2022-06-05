package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.VoteId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, VoteId> {

    Optional<Vote> findByIdAndMenuRequestId(VoteId id, Long menuRequestId);

}
