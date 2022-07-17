package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZoneId;

@Getter
@AllArgsConstructor
@Builder
public class MenuDto {
    private final Long id;

    @JsonProperty("written_at")
    private final Long writtenAt;

    @JsonProperty("menu_name")
    private final String menuName;

    private final String description;

    private final MenuCategory kind;

    private final Integer votes;

    private final MenuState menuState;

    private final Boolean isVoted;

    public MenuDto(MenuRequest menuRequest, User me) {
        this.id = menuRequest.getId();
        this.writtenAt = menuRequest.getCreateAt()
                .atZone(ZoneId.of("Asia/Seoul"))
                .toInstant()
                .toEpochMilli();
        this.menuName = menuRequest.getMenuName();
        this.description = menuRequest.getContent();
        this.kind = menuRequest.getMenuCategory();
        this.votes = (menuRequest.getVotes() == null) ? 0 : menuRequest.getVotes().size();
        this.menuState = menuRequest.getState();
        this.isVoted = menuRequest.getVotes().stream().filter(it -> it.getId().getId().getUser().getId().equals(me.getId())).count() == 1;
    }
}
