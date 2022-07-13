package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{user-id}/profile-image")
    public ResponseEntity<StreamingResponseBody> getProfileImage(@PathVariable("user-id") long userId) {
        return userService.getUserImage(userId);
    }

    @PatchMapping("/{user-id}/profile-image")
    public void setProfileImage(@PathVariable("user-id") long userId, @RequestParam MultipartFile file) {
        userService.setUserImage(userId, file);
    }

    @DeleteMapping("/{user-id}/profile-image")
    public void resetProfileImage(@PathVariable("user-id") long userId) {
        userService.resetUserImage(userId);
    }

    @GetMapping("/{user-id}")
    public UserResponseDto getUserInfo(@PathVariable("user-id") long userId) {
        return userService.getUser(userId);
    }
}
