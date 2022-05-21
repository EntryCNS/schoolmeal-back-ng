package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.commonuser.entity.CommonUser;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.entity.OAuthUser;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 14)
    private String name;

    @NotNull
    @ColumnDefault(value = "'USER'")
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToOne
    private CommonUser commonUser;

    @OneToOne
    private OAuthUser oAuthUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        if(commonUser != null) {
            return commonUser.getPassword();
        }else {
            return null;
        }
    }

    @Override
    public String getUsername() {
        if(commonUser != null) {
            return commonUser.getLoginId();
        }else if(oAuthUser != null) {
            return String.valueOf(id);
        }else {
            return null;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
