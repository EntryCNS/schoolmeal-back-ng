package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service.MenuService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Resource(name = "MenuServiceImpl")
    private final MenuService menuService;

    private final UserRepository userRepository;

    @GetMapping
    public MenuListDto findAllMenu(@RequestParam long page) {
        return menuService.findAllMenus(page);
    }
}
