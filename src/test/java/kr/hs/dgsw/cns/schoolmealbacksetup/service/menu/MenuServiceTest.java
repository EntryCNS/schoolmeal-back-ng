package kr.hs.dgsw.cns.schoolmealbacksetup.service.menu;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.MenuRequestRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service.MenuServiceImpl;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRequestRepository menuRequestRepository;

    @InjectMocks
    private MenuServiceImpl menuService;

    private User user() {
        return User.builder()
                .id(1L)
                .name("테스터")
                .role(UserRole.USER)
                .openId("1111")
                .build();
    }
    
    private MenuCreationDto menuCreationDto() {
        return MenuCreationDto.builder()
                .menuName("김밥")
                .description("참치 김밥")
                .kind(MenuCategory.KOREAN)
                .build();
    }

    @DisplayName("메뉴 추가 테스트")
    @Test
    void addMenu() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto();

        doReturn(MenuRequest.builder()
                .user(user())
                .menuCategory(menuCreationDto.getKind())
                .state(MenuState.STANDBY)
                .content(menuCreationDto.getDescription())
                .menuName(menuCreationDto.getMenuName())
                .createAt(LocalDateTime.now())
                .build()).when(menuRequestRepository)
                .save(any(MenuRequest.class));

        // when
        MenuDto menuDto = menuService.addMenu(user(), menuCreationDto);

        // then
        assertThat(menuDto.getKind()).isEqualTo(menuCreationDto.getKind());
        assertThat(menuDto.getDescription()).isEqualTo(menuCreationDto.getDescription());
        assertThat(menuDto.getMenuName()).isEqualTo(menuCreationDto.getMenuName());

        // verify
        verify(menuRequestRepository, times(1))
                .save(any(MenuRequest.class));
    }

}
