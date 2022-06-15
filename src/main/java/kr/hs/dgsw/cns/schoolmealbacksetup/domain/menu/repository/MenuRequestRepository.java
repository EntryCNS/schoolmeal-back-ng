package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestRepository extends JpaRepository<MenuRequest, Long> {

    List<MenuRequest> findTop10ByOrderByCreateAtDesc();
}
