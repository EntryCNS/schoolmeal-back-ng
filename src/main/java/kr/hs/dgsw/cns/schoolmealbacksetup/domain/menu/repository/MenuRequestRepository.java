package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MenuRequestRepository extends JpaRepository<MenuRequest, Long> {

    @Query("SELECT menu FROM MenuRequest menu WHERE menu.state=:state AND menu.acceptedAt=:date ORDER BY menu.createAt")
    Page<MenuRequest> findAllByStateAndAcceptedDate(MenuState state, LocalDateTime date, Pageable pageRequest);

    @Query("SELECT menu FROM MenuRequest menu WHERE menu.state=:state ORDER BY menu.createAt")
    Page<MenuRequest> findAllByState(MenuState state, Pageable pageRequest);
}
