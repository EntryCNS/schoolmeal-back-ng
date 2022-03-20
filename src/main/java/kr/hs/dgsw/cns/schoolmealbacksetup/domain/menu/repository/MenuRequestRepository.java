package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRequestRepository extends JpaRepository<MenuRequest, Long> {
}
