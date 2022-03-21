package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class AuthIdToken{

    @EmbeddedId
    private AuthId authId;

    @Column(nullable = false)
    private Long id;

}
