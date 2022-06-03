package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuStateDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.MenuRequestRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service(value = "MenuServiceImpl")
public class MenuServiceImpl implements MenuService {

    private final MenuRequestRepository menuRequestRepository;

    @Override
    public MenuListDto findAllMenus(long page) {
        List<MenuRequest> menuRequests =
                menuRequestRepository.findTop10ByOrderByCreateAtDesc();
        long pageCount = (menuRequestRepository.count() / 10 == 0) ? 1 : menuRequestRepository.count();
        List<MenuDto> menuDtos = menuRequests.stream()
                .map(MenuDto::new)
                .collect(Collectors.toList());

        return MenuListDto.builder()
                .page((int) page)
                .pageCount((int) pageCount)
                .result(menuDtos)
                .build();
    }

    @Override
    @Transactional
    public MenuDto addMenu(User user, MenuCreationDto menuCreationDto) {
        return null;
    }

    @Override
    public MenuDto findById(long menuId) {
        return null;
    }

    @Override
    public void addVote(User user, long menuId) {

    }

    @Override
    public void cancelVote(User user, long menuId) {

    }

    @Override
    public MenuDto updateState(User user, long menuId, MenuStateDto menuStateDto) {
        return null;
    }
}