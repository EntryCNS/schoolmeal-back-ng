package kr.hs.dgsw.cns.schoolmealbacksetup.service.menu;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.VoteId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuStateDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.MenuRequestRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.VoteRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service.MenuServiceImpl;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.AuthId;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.facade.UserFacade;
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
class MenuServiceTest {

    @Mock
    private MenuRequestRepository menuRequestRepository;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private UserFacade userFacade;

    @InjectMocks
    private MenuServiceImpl menuService;

    private User user() {
        return User.builder()
                .id(1L)
                .name("?????????")
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

    private Vote toEntity(MenuRequest menuRequest, User user) {
        return Vote.builder()
                .id(new VoteId(new AuthId(user)))
                .menuRequest(menuRequest)
                .build();
    }


    @DisplayName("?????? ?????? ?????????")
    @Test
    void addMenu() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("??????", "?????? ??????");
        lenient().doReturn(toEntity(menuCreationDto)).when(menuRequestRepository)
                .save(any(MenuRequest.class));

        lenient().when(userFacade.getCurrentUser()).thenReturn(user());

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

    @DisplayName("id??? ?????? ??????")
    @Test
    void findMenuById() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("?????????", "???????????? ???????????????.");
        lenient().doReturn(toEntity(menuCreationDto)).when(menuRequestRepository)
                .save(any(MenuRequest.class));
        long id = 1L;
        lenient().when(menuRequestRepository.findById(id))
                .thenReturn(Optional.of(toEntity(menuCreationDto)));

        lenient().when(userFacade.getCurrentUser()).thenReturn(user());

        // when
        MenuDto menuDto = menuService.findById(id);

        // then
        assertThat(menuDto).isNotNull();
        assertThat(menuDto.getId()).isEqualTo(id);
        assertThat(menuDto.getMenuName()).isEqualTo(menuCreationDto.getMenuName());
    }

    @DisplayName("id ??? ?????? ??????")
    @Test
    void addVoteMenu() {
        // given
        MenuCreationDto menuCreationDto = menuCreationDto("????????? ??????", "???????????? ?????? ?????????.");
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

    @DisplayName("id ??? ?????? ??????")
    @Test
    void cancelVoteMenu() {
        // given
        User user = user();
        MenuCreationDto menuCreationDto = menuCreationDto("??????", "?????? ????????? ???????????? ?????? ????????????.");
        MenuRequest menuRequest = toEntity(menuCreationDto, new HashSet<>());
        Vote vote = toEntity(menuRequest, user);
        menuRequest.addVote(vote);

        lenient().when(menuRequestRepository.findById(anyLong()))
                .thenReturn(Optional.of(menuRequest));
        lenient().when(voteRepository.findByIdIdUserAndMenuRequest(user, menuRequest))
                .thenReturn(Optional.of(vote));
        // when
        menuService.cancelVote(user, anyLong());

        // then
        assertEquals(0, menuRequest.getVotes().size());

        // verity
        verify(voteRepository, times(1))
                .delete(vote);
    }

    @DisplayName("?????? ?????? ????????????")
    @Test
    void menuStateTest() {
        // given
        MenuRequest menuRequest = toEntity(menuCreationDto("??????", "?????? ??????"));
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
