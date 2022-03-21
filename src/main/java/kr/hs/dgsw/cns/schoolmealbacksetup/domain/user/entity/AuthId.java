package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class AuthId implements Serializable {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser(){
        return user;
    }
}
