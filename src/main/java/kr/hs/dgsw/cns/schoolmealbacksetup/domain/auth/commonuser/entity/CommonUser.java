package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.commonuser.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.entity.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommonUser implements Serializable {

    @EmbeddedId
    private UserId userId;

    @NotNull
    @Column(unique = true)
    @Length(max = 100)
    private String loginId;

    @NotNull
    @Column(unique = true)
    @Length(max = 128)
    private String email;

    @NotNull
    @Length(max = 256)
    private String password;


}