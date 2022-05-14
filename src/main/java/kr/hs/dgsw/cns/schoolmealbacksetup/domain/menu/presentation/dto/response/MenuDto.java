package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.entity.MenuRequest;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import lombok.Getter;

import java.time.ZoneId;

@Getter
public class MenuDto {
    private final Long id;

    @JsonProperty("written_at")
    private final Long writtenAt;

    @JsonProperty("menu_name")
    private final String menuName;

    private final String description;

    private final MenuCategory kind;

    private final Integer votes;

    public MenuDto(MenuRequest menuRequest) {
        this.id = menuRequest.getId();
        this.writtenAt = menuRequest.getCreateAt()
                .atZone(ZoneId.of("Asia/Seoul"))
                .toInstant()
                .toEpochMilli();
        this.menuName = menuRequest.getMenuName();
        this.description = menuRequest.getContent();
        this.kind = menuRequest.getMenuCategory();
        this.votes = menuRequest.getVotes().size();
    }
}
