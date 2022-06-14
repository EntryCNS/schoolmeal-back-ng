package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.facade.UserFacade;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service.UserService;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service.UserServiceImpl;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ResponseLink;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.*;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFacade userFacade;

    private UserService userService;

    private final User user = User.builder()
            .id(0L)
            .openId("012345")
            .role(UserRole.USER)
            .name("테스트 유저")
            .build();
    private final Optional<User> optionalUser = Optional.of(user);

    @BeforeEach
    public void initMocks() {
        userService = new UserServiceImpl(userRepository, userFacade);
    }

    @DisplayName("사용자 가져오기")
    @Test @Order(0)
    void getUser() {
        when(userRepository.findById(user.getId())).thenReturn(optionalUser);

        UserResponseDto found = userService.getUser(user.getId());
        UserResponseDto expected = UserResponseDto.builder()
                .name(user.getName())
                .profileImage(new ResponseLink("link", "GET", String.format("/users/%d/profile-image", user.getId())))
                .build();

        System.out.println("Found: " + found.toString());
        System.out.println("Expected: " + expected.toString());

        assertThat(found).isEqualTo(expected);
    }

    private final MockMultipartFile userProfileImage = new MockMultipartFile("test.png", "TEST FILE".getBytes());

    @DisplayName("사용자 프로필사진 설정")
    @Test @Order(1)
    void setUserProfileImage() {
        new File("../storages/profile/img").mkdirs();
        try { new File("../storages/profile/img/default").createNewFile(); } catch (IOException ignored) {}

        userService.setUserImage(user.getId(), userProfileImage);
        assertThatNoException();
    }

    @DisplayName("사용자 프로필사진 조회 (커스텀)")
    @Test @Order(2)
    void getUserProfileImageWithCustom() {
        when(userRepository.findById(user.getId())).thenReturn(optionalUser);

        ResponseEntity<StreamingResponseBody> response = userService.getUserImage(user.getId());

        byte[] body = null;
        try {
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            response.getBody().writeTo(data);
            body = data.toByteArray();

            assertThat(body).isEqualTo(userProfileImage.getBytes());
        }catch (IOException ex) { ex.printStackTrace(); }
    }

    @DisplayName("사용자 프로필사진 초기화")
    @Test @Order(3)
    void resetUserProfileImage() {
        userService.resetUserImage(user.getId());
        assertThatNoException();
    }

    @DisplayName("사용자 프로필사진 조회 (기본값)")
    @Test @Order(4)
    void getUserProfileImageWithDefault() {
        when(userRepository.findById(user.getId())).thenReturn(optionalUser);

        ResponseEntity<StreamingResponseBody> response = userService.getUserImage(user.getId());

        byte[] body = null;
        try {
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            response.getBody().writeTo(data);
            body = data.toByteArray();

            assertThat(body).isNotEqualTo(userProfileImage.getBytes());
        }catch (IOException ex) { ex.printStackTrace(); }
    }

}
