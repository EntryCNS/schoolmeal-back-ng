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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

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
    
    private MenuCreationDto menuCreationDto(String name, String description) {
        return MenuCreationDto.builder()
                .menuName(name)
                .description(description)
                .kind(MenuCategory.KOREAN)
                .build();
    }

    private MenuRequest toEntity(MenuCreationDto menuCreationDto) {
        return MenuRequest.builder()
                .id(1L)
                .createAt(LocalDateTime.now())
                .user(user())
                .menuName(menuCreationDto.getMenuName())
                .content(menuCreationDto.getDescription())
                .menuCategory(menuCreationDto.getKind())
                .state(MenuState.STANDBY)
                .build();
    }

//    private MenuRequest save(MenuCreationDto menuCreationDto) {
//        return doReturn(toEntity(menuCreationDto)).when(menuRequestRepository)
//                .save(any(MenuRequest.class));
//    }

    @Order(1)
    @DisplayName("메뉴 추가 테스트")
    @Test
    void addMenu() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("김밥", "참치 김밥");

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

    @Order(2)
    @DisplayName("id 로 메뉴 조회")
    @Test
    void findMenuById() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("규카츠", "규카츠는 맛있습니다.");
        lenient().doReturn(toEntity(menuCreationDto)).when(menuRequestRepository)
                .save(any(MenuRequest.class));
        long id = 1L;
        when(menuRequestRepository.findById(id))
                .thenReturn(Optional.of(toEntity(menuCreationDto)));

        // when
        MenuDto menuDto = menuService.findById(id);

        // then
        assertThat(menuDto).isNotNull();
        assertThat(menuDto.getId()).isEqualTo(id);
        assertThat(menuDto.getMenuName()).isEqualTo(menuCreationDto.getMenuName());
    }
}
