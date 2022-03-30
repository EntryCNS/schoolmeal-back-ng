package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MenuDto {
    private final Long id;

    private final Long written_at;

    private final String menu_name;

    private final String description;

    private final MenuCategory kind;

    private final Integer votes;
}
