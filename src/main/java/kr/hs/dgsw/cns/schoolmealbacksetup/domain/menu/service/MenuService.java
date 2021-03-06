package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuSelectionType;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuStateDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.PlannerDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.neis.MealPlannerInfra;

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
     * @param selectionType 원하는 타입
     * @return {@link MenuListDto}
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.PageCannotNegative
     * page 파라미터가 음수인 경우 발생합니다
     */
    MenuListDto findAllMenus(int page, MenuSelectionType selectionType);

    /**
     * <h2>메뉴 추가하기</h2>
     * 메뉴 생성 dto 필드를 사용하여 메뉴를 추가합니다
     * @param menuCreationDto 메뉴 생성 DTO
     * @param user 작성자
     * @see MenuCreationDto
     * @return {@link MenuDto}
     */
    MenuDto addMenu(User user, MenuCreationDto menuCreationDto);

    /**
     * <h2>특정 메뉴 가져오기</h2>
     * 메뉴 id로 해당 메뉴를 찾습니다
     * @param menuId 메뉴 Id
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.CannotFound
     * {@code menuId}를 가진 메뉴를 찾지 못한 경우 발생합니다
     * @return {@link MenuDto}
     */
    MenuDto findById(long menuId);

    /**
     * <h2>투표 추가</h2>
     * 메뉴 id로 해당 메뉴에 투표를 합니다
     * @param menuId 메뉴 id
     * @param user 투표자
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.CannotFound
     * {@code menuId}를 가진 메뉴를 찾지 못한 경우 발생합니다
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote.AlreadyVoted
     * {@code menuId}에 이미 투표를 진행한 경우 발생합니다
     */
    void addVote(User user, long menuId);

    /**
     * <h2>투표 취소</h2>
     * 메뉴 id로 해당 메뉴에 했던 투표를 취소합니다
     * @param menuId 메뉴 id
     * @param user 투표 취소자
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.CannotFound
     * {@code menuId}를 가진 메뉴를 찾지 못한 경우 발생합니다
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote.NeverVoted
     * {@code menuId}에 투표를 진행한 적이 없는 경우 발생합니다
     */
    void cancelVote(User user, long menuId);

    /**
     * <h2>메뉴 상태 설정</h2>
     * boolean 을 enum 으로 치환되어 저장됩니다
     * true 인 경우 ALLOWED, false 인 경우 DENIED
     * @implNote 해당 메소드를 호출 전까지는 STANDBY 상태입니다
     * @apiNote {@code user}는 반드시 선생님 권한이 요구됩니다.
     * @param user 메뉴 상태 설정자
     * @param menuId 메뉴 id
     * @param menuStateDto boolean 형태의 상태
     * @throws kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest.CannotFound
     * {@code menuId}를 가진 메뉴를 찾지 못한 경우 발생합니다
     * @return MenuDto
     * @see kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState
     */
    MenuDto updateState(User user, long menuId, MenuStateDto menuStateDto);

    /**
     * <h2>식단표 조회</h2>
     * @param year 가져올 년도
     * @param month 가져올 월
     * @param day 가져올 일자
     * @throws MealPlannerInfra.MealParseFailedException
     * 크롤링이 실패했을 때 발생합니다
     * @return PlannerDto
     */
    PlannerDto getMenuPlanner(int year, int month, int day);
}
