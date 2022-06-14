package kr.hs.dgsw.cns.schoolmealbacksetup.controller.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service.AuthDetailsService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.Vote;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.MenuController;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request.MenuCreationDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response.MenuDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.repository.MenuRequestRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.service.MenuService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtConfiguration;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.security.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
@EnableConfigurationProperties(value = JwtConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean(name = "MenuServiceImpl")
    private MenuService menuService;
    
    @SpyBean
    private JwtProvider jwtProvider;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private AuthDetailsService authDetailsService;

    @Mock
    private MenuRequestRepository menuRequestRepository;
    
    private final LocalDateTime dateTime = LocalDateTime.now();

    private User user() {
        return User.builder()
                .id(1L)
                .name("tester")
                .role(UserRole.USER)
                .openId("open_id")
                .build();
    }

    private MenuRequest toEntity(MenuCreationDto menuCreationDto, Set<Vote> votes) {
        return toEntity(
                menuCreationDto.getMenuName(),
                menuCreationDto.getDescription(),
                menuCreationDto.getKind(),
                votes
        );
    }

    private MenuRequest toEntity(String menuName,
                                 String description,
                                 MenuCategory menuCategory,
                                 Set<Vote> votes) {
        return MenuRequest.builder()
                .id(1L)
                .createAt(dateTime)
                .user(user())
                .menuName(menuName)
                .content(description)
                .menuCategory(menuCategory)
                .state(MenuState.STANDBY)
                .votes(votes)
                .build();
    }

    private String token() {
        return String.format("Bearer %s", jwtProvider.generateAccessToken("1"));
    }

    @DisplayName("메뉴 추가 성공")
    @RepeatedTest(10)
    void addMenuSuccess() throws Exception {
        // given
        MenuCreationDto menuCreationDto = new MenuCreationDto(MenuCategory.KOREAN, "김밥", "참치 김밥");
        lenient().when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user()));
        lenient().when(menuService.addMenu(any(), any()))
                .thenReturn(new MenuDto(toEntity(menuCreationDto, new HashSet<>())));

        String token = token();
        String content = objectMapper.writeValueAsString(menuCreationDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/menu")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.menu_name", "김밥").exists())
                .andExpect(jsonPath("$.description", "참치 김밥").exists())
                .andExpect(jsonPath("$.kind", MenuCategory.KOREAN).exists());
    }

    @DisplayName("메뉴 추가 실패")
    @Test
    void addMenuFailed() throws Exception {
        // given
        MenuCreationDto menuCreationDto = new MenuCreationDto(MenuCategory.KOREAN, "김밥", "참치 김밥");
        lenient().when(menuService.addMenu(any(), any()))
                .thenReturn(new MenuDto(toEntity(menuCreationDto, new HashSet<>())));
        String token = token();
        String content = objectMapper.writeValueAsString(menuCreationDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", token)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("id로 메뉴 조회 성공")
    @Test
    void findByIdSuccess() throws Exception {
        // given
        MenuRequest menuRequest = toEntity(
                "우동",
                "맛있는 우동",
                MenuCategory.JAPANESE,
                new HashSet<>()
        );
        MenuDto menuDto = new MenuDto(menuRequest);
        menuRequestRepository.save(
                menuRequest
        );
        lenient().when(menuService.findById(anyLong()))
                .thenReturn(menuDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/menu/1")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.menu_name", "우동").exists())
                .andExpect(jsonPath("$.description", "맛있는 우동").exists())
                .andExpect(jsonPath("$.kind", MenuCategory.JAPANESE).exists());
    }

    @DisplayName("메뉴 투표 성공")
    @Test
    void addVoteSuccess() throws Exception {
        // given
        // 유저 저장한 후 해당 메뉴에 투표를 진행한다.
        userRepository.save(user());
        MenuRequest menuRequest = toEntity(
                "쇠고기야채죽",
                "간 좀 맞춰주세요",
                MenuCategory.KOREAN,
                new HashSet<>()
        );
        menuRequestRepository.save(menuRequest);

        lenient().when(menuRequestRepository.findById(anyLong()))
                .thenReturn(Optional.of(menuRequest));
        lenient().when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user()));
        String token = token();

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/menu/1/votes")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @DisplayName("메뉴 투표 실패")
    @Test
    void addVoteFailed() throws Exception {
        // given
        MenuRequest menuRequest = toEntity(
                "쇠고기야채죽",
                "간 좀 맞춰주세요",
                MenuCategory.KOREAN,
                new HashSet<>()
        );
        menuRequestRepository.save(menuRequest);

        lenient().when(menuRequestRepository.findById(anyLong()))
                .thenReturn(Optional.of(menuRequest));
        String token = token();

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/menu/1/votes")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
