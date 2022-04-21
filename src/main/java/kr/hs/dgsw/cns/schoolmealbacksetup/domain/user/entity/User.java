package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class User {

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

}
