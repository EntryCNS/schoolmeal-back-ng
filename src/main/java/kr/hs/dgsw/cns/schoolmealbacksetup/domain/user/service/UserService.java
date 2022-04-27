package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.request.SignUpRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.SignUpResponse;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {

    SignUpResponse signIn(SignUpRequest signUpRequest);

    void setUserImage(Long userId, MultipartFile profileImage);

    void resetUserImage(Long userId);

    UserResponseDto inquireUser(Long userId);

}
