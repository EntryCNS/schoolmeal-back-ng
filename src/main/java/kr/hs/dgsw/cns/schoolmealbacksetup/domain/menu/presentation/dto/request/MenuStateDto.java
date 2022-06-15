package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class MenuStateDto {
    @NotNull
    private boolean accepted;
}
