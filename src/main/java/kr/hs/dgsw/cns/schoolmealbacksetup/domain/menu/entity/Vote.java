package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private VoteId id;
}
