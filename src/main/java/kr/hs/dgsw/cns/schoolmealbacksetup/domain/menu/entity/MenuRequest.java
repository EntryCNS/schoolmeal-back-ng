package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.exception.GlobalException;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class MenuRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    @ColumnDefault(value = "'STANDBY'")
    @Enumerated(EnumType.STRING)
    private MenuState state;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @Getter
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public static class PageCannotNegative extends GlobalException {
        @JsonProperty(value = "message")
        private static final String MESSAGE = "Parameter 'page' cannot be negative.";
    }

    @Getter
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class CannotFound extends GlobalException {
        private final String message;

        public CannotFound(long id) {
            this.message = String.format("cannot found menu such as id is '%d'", id);
        }
    }
}
