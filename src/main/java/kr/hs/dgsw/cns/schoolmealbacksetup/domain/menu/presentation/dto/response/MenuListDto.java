package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MenuListDto {
    private final Integer page;

    private final Integer page_count;

    private final List<MenuDto> result;
}
