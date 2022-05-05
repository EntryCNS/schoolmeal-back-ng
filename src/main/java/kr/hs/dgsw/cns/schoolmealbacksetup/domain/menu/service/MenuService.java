package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;

/**
 * <h1>메뉴 Service Interface</h1>
 * @see kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest
 */
public interface MenuService {

    /**
     * <h2>모든 메뉴 가져오기</h2>
     * 페이지 page 에 위치한 메뉴들을 반환합니다<br/>
     * 0보다 작은 경우, 예외가 발생합니다
     * @param page 메뉴 page
     * @return {@link MenuListDto}
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.PageCannotNegative page 파라미터가 음수일 경우 예외를 던집니다
     */
    MenuListDto findAllMenus(long page);

    /**
     * <h2>메뉴 추가하기</h2>
     * 메뉴 생성 dto 필드를 사용하여 메뉴를 추가합니다
     * @param menuCreationDto 메뉴 생성 DTO
     * @see MenuCreationDto
     * @return {@link MenuDto}
     */
    MenuDto addMenu(MenuCreationDto menuCreationDto);

    /**
     * <h2>특정 메뉴 가져오기</h2>
     * 메뉴의 id로 해당 메뉴를 찾습니다
     * @param menuId 메뉴의 Id
     * @return {@link MenuDto}
     */
    MenuDto findById(long menuId);
}
