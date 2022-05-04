package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;

/**
 * 메뉴와 관련된 API Interface 입니다.
 */
public interface MenuService {

    /**
     * <h2>모든 메뉴 가져오기</h2>
     * @param page long 기본형을 전체 메뉴의 페이지로 지정합니다.
     *             0보다 작은 경우, 예외가 발생합니다.
     * @return MenuListDto – 매개변수에 해당하는 페이즈를 JsonArray 에 담아 반환합니다
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.PageCannotNegative page 파라미터가 음수일 경우 예외를 던집니다.
     * @see MenuListDto
     * @see kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto
     */
    MenuListDto findAllMenus(long page);
}
