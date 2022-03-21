package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommonUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AuthId id;

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