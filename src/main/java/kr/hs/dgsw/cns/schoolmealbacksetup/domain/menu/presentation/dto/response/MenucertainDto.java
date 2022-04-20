package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MenucertainDto {

    private final Long id;

    @JsonProperty("written_at")
    private final Long writtenAt;

    @JsonProperty("menu_Name")
    private final String menuName;

    private final String description;

    private final MenuCategory kind;

    private final Integer votes;
}
