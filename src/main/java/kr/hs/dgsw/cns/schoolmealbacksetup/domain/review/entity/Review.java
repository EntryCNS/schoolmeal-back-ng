package kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.review.type.ReviewTime;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDate date;

    @NotNull
    private ReviewTime reviewTime;

    @NotNull
    @Length(max = 200)
    private String message;

    @NotNull
    private int rate;

}
