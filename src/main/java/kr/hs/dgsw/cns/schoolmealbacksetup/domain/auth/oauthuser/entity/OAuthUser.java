package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.oauthuser.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.entity.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class OAuthUser implements Serializable {

    @EmbeddedId
    private UserId userId;

    @NotNull
    private String token;

}
