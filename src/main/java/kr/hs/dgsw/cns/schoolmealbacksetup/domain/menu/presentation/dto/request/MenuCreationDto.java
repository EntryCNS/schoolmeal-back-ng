package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MenuCreationDto {
    @NotNull
    private MenuCategory kind;

    @NotNull
    @Size(max = 20)
    @JsonProperty(value = "menu_name")
    private String menuName;

    @NotNull
    @Size(max = 500)
    private String description;
}
