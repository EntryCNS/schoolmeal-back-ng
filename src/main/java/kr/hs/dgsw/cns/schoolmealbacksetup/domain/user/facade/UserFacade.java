package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.facade;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void verifyQueryUserEquals(long userId) {
        User queryUser = getCurrentUser();
        if(!queryUser.getId().equals(userId)) throw new User.UnauthorizedException();
    }
}
