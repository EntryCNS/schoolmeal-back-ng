package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.VoteId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuSelectionType;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuStateDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.PlannerDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.MenuRequestRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.VoteRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.AuthId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.neis.MealPlannerInfra;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service(value = "MenuServiceImpl")
public class MenuServiceImpl implements MenuService {

    private final MenuRequestRepository menuRequestRepository;
    private final VoteRepository voteRepository;

    private final MealPlannerInfra mealPlannerInfra;

    @Override
    @Transactional(readOnly = true)
    public MenuListDto findAllMenus(int page, MenuSelectionType selectionType) {

        Page<MenuRequest> menuPage;
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createAt").descending());

        if(MenuSelectionType.ACCEPTED_TODAY == selectionType) {
            menuPage = menuRequestRepository.findAllByStateAndAcceptedDate(MenuState.ALLOWED, LocalDateTime.now(), pageRequest);
        } else {
            menuPage = menuRequestRepository.findAllByState(selectionType.toEntityType(), pageRequest);
        }

        List<MenuDto> menuDtos = menuPage.stream()
                .map(MenuDto::new)
                .collect(Collectors.toList());

        return MenuListDto.builder()
                .page(page)
                .pageCount(menuPage.getTotalPages())
                .result(menuDtos)
                .build();
    }

    @Override
    @Transactional
    public MenuDto addMenu(User user, MenuCreationDto menuCreationDto) {
        MenuRequest menuRequest = MenuRequest.builder()
                .user(user)
                .menuName(menuCreationDto.getMenuName())
                .content(menuCreationDto.getDescription())
                .state(MenuState.STANDBY)
                .menuCategory(menuCreationDto.getKind())
                .build();

        MenuRequest savedRequest = menuRequestRepository.save(menuRequest);
        return new MenuDto(savedRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public MenuDto findById(long menuId) {
        MenuRequest menuRequest = menuRequestRepository.findById(menuId)
                .orElseThrow(() -> new MenuRequest.CannotFound(menuId));
        return new MenuDto(menuRequest);
    }

    @Override
    @Transactional
    public void addVote(User user, long menuId) {
        MenuRequest menuRequest = menuRequestRepository.findById(menuId)
                .orElseThrow(() -> new MenuRequest.CannotFound(menuId));

        if(menuRequest.getState() != MenuState.STANDBY) throw new Vote.NotVotable();

        if (voteRepository.existsByIdIdUser(user)) {
            // 존재한다면 이미 투표되었다고 409 예외 발생
            throw new Vote.AlreadyVoted();
        }
        VoteId voteId = new VoteId(new AuthId(user));

        Vote vote = Vote.builder()
                .id(voteId)
                .menuRequest(menuRequest)
                .build();

        menuRequest.addVote(vote);
    }

    @Override
    @Transactional
    public void cancelVote(User user, long menuId) {
        MenuRequest menuRequest = menuRequestRepository.findById(menuId)
                .orElseThrow(() -> new MenuRequest.CannotFound(menuId));
        if(menuRequest.getState() != MenuState.STANDBY) throw new Vote.NotVotable();

        Vote vote = voteRepository.findByIdIdUserAndMenuRequest(user, menuRequest)
                        .orElseThrow(Vote.NeverVoted::new);

        menuRequest.removeVote(vote);
        voteRepository.delete(vote);
    }

    @Override
    @Transactional
    public MenuDto updateState(User user, long menuId, MenuStateDto menuStateDto) {
        MenuRequest menuRequest = menuRequestRepository.findById(menuId)
                .orElseThrow(() -> new MenuRequest.CannotFound(menuId));
        menuRequest.setMenuState(convertFrom(menuStateDto));

        return new MenuDto(menuRequest);
    }

    @Override
    public PlannerDto getMenuPlanner(int year, int month, int day) {
        return new PlannerDto(mealPlannerInfra.getMealsOfDate(year, month, day));
    }

    private MenuState convertFrom(MenuStateDto menuStateDto) {
        return (menuStateDto.isAccepted()) ? MenuState.ALLOWED : MenuState.DENIED;
    }


}
