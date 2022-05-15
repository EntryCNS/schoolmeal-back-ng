package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vote {
    @EmbeddedId
    private VoteId id;

    @ManyToOne
    @JoinColumn(name = "menu_request_id", nullable = false, unique = true)
    private MenuRequest menuRequest;

    public void setMenuRequest(MenuRequest menuRequest) {
        this.menuRequest = menuRequest;
    }
}
