package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.service;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.oauthuser.repository.OAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final OAuthRepository oAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return oAuthRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not exists"))
                .getUserId()
                .getUser();
    }
}
