package kr.hs.dgsw.cns.schoolmealbacksetup.service.menu;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.VoteId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuStateDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.MenuRequestRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.VoteRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service.MenuServiceImpl;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.AuthId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRequestRepository menuRequestRepository;

    @Mock
    private VoteRepository voteRepository;

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
        return toEntity(menuCreationDto, new HashSet<>());
    }

    private MenuRequest toEntity(MenuCreationDto menuCreationDto, Set<Vote> votes) {
        return MenuRequest.builder()
                .id(1L)
                .createAt(LocalDateTime.now())
                .user(user())
                .menuName(menuCreationDto.getMenuName())
                .content(menuCreationDto.getDescription())
                .menuCategory(menuCreationDto.getKind())
                .state(MenuState.STANDBY)
                .votes(votes)
                .build();
    }

    private Vote toEntity(MenuRequest menuRequest) {
        return Vote.builder()
                .id(new VoteId(new AuthId(user())))
                .menuRequest(menuRequest)
                .build();
    }


    @DisplayName("메뉴 추가 테스트")
    @Test
    void addMenu() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("김밥", "참치 김밥");
        lenient().doReturn(toEntity(menuCreationDto)).when(menuRequestRepository)
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

    @DisplayName("id로 메뉴 조회")
    @Test
    void findMenuById() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("규카츠", "규카츠는 맛있습니다.");
        lenient().doReturn(toEntity(menuCreationDto)).when(menuRequestRepository)
                .save(any(MenuRequest.class));
        long id = 1L;
        lenient().when(menuRequestRepository.findById(id))
                .thenReturn(Optional.of(toEntity(menuCreationDto)));

        // when
        MenuDto menuDto = menuService.findById(id);

        // then
        assertThat(menuDto).isNotNull();
        assertThat(menuDto.getId()).isEqualTo(id);
        assertThat(menuDto.getMenuName()).isEqualTo(menuCreationDto.getMenuName());
    }

    @DisplayName("id 로 메뉴 투표")
    @Test
    void addVoteMenu() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("바나나 우유", "바나나는 원래 하얗다.");
        MenuRequest menuRequest = toEntity(menuCreationDto);

        lenient().doReturn(menuRequest).when(menuRequestRepository)
                .save(any(MenuRequest.class));
        long id = 1L;
        lenient().when(menuRequestRepository.findById(id))
                .thenReturn(Optional.of(menuRequest));

        // when
        menuService.addVote(user(), id);

        // then
        assertEquals(1, menuRequest.getVotes().size());
    }

    @DisplayName("id 로 투표 취소")
    @Test
    void cancelVoteMenu() {
        // given
        User user = user();
        MenuCreationDto menuCreationDto = menuCreationDto("치킨", "아주 훌륭한 닭다리가 먹고 싶습니다.");
        MenuRequest menuRequest = toEntity(menuCreationDto, new HashSet<>());
        Vote vote = toEntity(menuRequest);
        menuRequest.addVote(vote);

        lenient().when(menuRequestRepository.findById(anyLong()))
                .thenReturn(Optional.of(menuRequest));
        lenient().when(voteRepository.findById_Id_User_AndMenuRequest(user, menuRequest))
                .thenReturn(Optional.of(vote));
        // when
        menuService.cancelVote(user, anyLong());

        // then
        assertEquals(0, menuRequest.getVotes().size());

        // verity
        verify(voteRepository, times(1))
                .delete(vote);
    }

    @DisplayName("메뉴 상태 업데이트")
    @Test
    void menuStateTest() {
        // given
        MenuRequest menuRequest = toEntity(menuCreationDto("피자", "치즈 피자"));
        lenient().doReturn(menuRequest).when(menuRequestRepository)
                .save(any(MenuRequest.class));
        menuRequestRepository.save(any(MenuRequest.class));

        lenient().when(menuRequestRepository.findById(1L))
                .thenReturn(Optional.of(menuRequest));
        MenuStateDto menuStateDto = new MenuStateDto(true);

        // when
        MenuDto menuDto = menuService.updateState(user(), 1L, menuStateDto);

        // then
        assertEquals(MenuState.ALLOWED, menuDto.getMenuState());
    }

}
