package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    public static class AlreadyVoted extends BusinessException {
        public AlreadyVoted() {
            super(HttpStatus.CONFLICT, "이미 투표된 메뉴입니다.");
        }
    }
    
    public static class NeverVoted extends BusinessException {
        public NeverVoted() {
            super(HttpStatus.CONFLICT, "투표를 하지 않았습니다.");
        }
    }


}
