package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.facade.UserFacade;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ResponseLink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private static final File defaultProfileImage = new File("../storages/profile/img/default");

    private File getProfileImageFile(long userId) {
        return new File(String.format("../storages/profile/img/%d", userId));
    }

    private Optional<File> getProfileImageOptional(long userId) {
        File file = getProfileImageFile(userId);
        if(!file.exists()) return Optional.empty();

        return Optional.of(file);
    }

    //프사 가져오기
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<StreamingResponseBody> getUserImage(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(User.UserNotFoundException::new);

        File imageFile = getProfileImageOptional(user.getId())
                .orElse(defaultProfileImage);

        StreamingResponseBody response = outputStream -> {
            FileInputStream imageFileReader = new FileInputStream(imageFile);
            byte[] buffer = new byte[1024];
            int read;
            while((read = imageFileReader.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
                outputStream.flush();
            }
            imageFileReader.close();
            outputStream.close();
        };

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //사용자 프로필 변경하기
    @Override
    public void setUserImage(long userId, MultipartFile profileImage) {
        userFacade.verifyQueryUserEquals(userId);

        File profileFile = getProfileImageFile(userId);

        try {
            if(profileFile.exists()) Files.delete(profileFile.toPath());
            if(!profileFile.createNewFile()) throw new User.UserProfileCreationException();

            FileOutputStream profileWriter = new FileOutputStream(profileFile);
            profileWriter.write(profileImage.getBytes());
            profileWriter.close();
        }catch (IOException ex) {
            throw new User.UserProfileCreationException();
        }
    }

    //기본 사진으로 되돌리기
    @Override
    public void resetUserImage(long userId) {
        userFacade.verifyQueryUserEquals(userId);

        File imageFileToDelete = getProfileImageOptional(userId)
                .orElseThrow(User.UserProfileAlreadyDefaultException::new);

        try {
            Files.delete(imageFileToDelete.toPath());
        }catch (IOException ex) { throw new User.UserProfileResetFailedException(); }
    }

    //유저 조회
    @Override
    public UserResponseDto getUser(Long userId) {
        User targetUser = userRepository.findById(userId)
                .orElseThrow(User.UserNotFoundException::new);

        return UserResponseDto.builder()
                .name(targetUser.getName())
                .profileImage(new ResponseLink("link", "GET", String.format("/users/%d/profile-image", targetUser.getId())))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByOpenId(userName)
                .orElseThrow(User.UserNotFoundException::new);
    }

}
