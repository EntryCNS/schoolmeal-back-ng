package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.service;


import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserImageAcceptedDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response.UserResponseDto;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserImageAcceptedDto userImageAcceptedDto;

    //프사 가져오기
    @Override
    @Transactional(readOnly = true)
    public byte[] getUserImage(long userId) {
        try (final ByteArrayInputStream inputStream
                     = new ByteArrayInputStream(getUserImage(userId)))  {
            ImageIO.write(ImageIO.read(inputStream),
                    "", new File(""));
        } catch (IOException e) {
            e.printStackTrace();

            //파일이 빈 것이 들어올 경우 기본 프로필 사진으로 변경.
//            if(getUserImage(userId).length <= 0){
//                userRepository.deleteById(userId);
//            }
        }

        //파일이 빈 것이 들어올 경우 기본 프로필 사진으로 변경.
        if(getUserImage(userId).length <= 0){
            userRepository.deleteById(userId);
        }
        else{

        }


        return new byte[];
    }

    //사용자 프로필 변경하기
    @Override
    public UserImageAcceptedDto setUserImage(long userId, MultipartFile profileImage) {

        User user = userRepository.findById(userId)
                .orElseThrow();
        return UserImageAcceptedDto.fromUser(user);
    }

    //기본 사진으로 되돌리기
    @Override
    public void resetUserImage(long userId) {

        //delete 하는 방법
        userRepository.deleteById(userId);
    }

    //유저 조회
    @Override
    public UserResponseDto getUser(Long userId) {

        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findAll().orElseThrow(()-> new UsernameNotFoundException("not found username : " + username));

        return loadUserByUsername(username);
    }

}
