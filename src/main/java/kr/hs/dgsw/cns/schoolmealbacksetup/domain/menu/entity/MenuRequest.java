package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MenuRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createAt;

    @NotNull
    @Length(max = 128)
    private String menuName;

    @NotNull
    @Length(max = 512)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "STANDBY")
    private MenuState state;
}