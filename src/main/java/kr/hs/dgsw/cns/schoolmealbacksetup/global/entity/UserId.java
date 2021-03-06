package kr.hs.dgsw.cns.schoolmealbacksetup.global.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class UserId implements Serializable {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
