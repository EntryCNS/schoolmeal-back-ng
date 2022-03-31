package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MenuListDto {
    private final Integer page;

    @JsonProperty("page_count")
    private final Integer pageCount;

    private final List<MenuDto> result;
}
