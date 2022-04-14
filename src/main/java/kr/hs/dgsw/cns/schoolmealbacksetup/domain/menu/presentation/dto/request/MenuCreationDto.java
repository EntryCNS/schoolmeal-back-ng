package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class MenuCreationDto {
    @NotNull
    private MenuCategory kind;

    @NotNull
    @Size(max = 20)
    private String menuName;

    @NotNull
    @Size(max = 500)
    private String description;
}
