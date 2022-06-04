package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuListDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service.MenuService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Resource(name = "MenuServiceImpl")
    private final MenuService menuService;

    @GetMapping
    public MenuListDto findAllMenu(@RequestParam long page) {
        return menuService.findAllMenus(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDto addMenu(@RequestBody @Valid MenuCreationDto menuCreationDto, @AuthenticationPrincipal User principal) {
        if (principal == null) throw new User.UnauthorizedException();
        return menuService.addMenu(principal, menuCreationDto);
    }
}
