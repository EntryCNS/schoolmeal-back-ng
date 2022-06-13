package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface UserService extends UserDetailsService {

    ResponseEntity<StreamingResponseBody> getUserImage(long userId);

    void setUserImage(long userId, MultipartFile profileImage);

    void resetUserImage(long userId);

    UserResponseDto getUser(Long userId);

}
