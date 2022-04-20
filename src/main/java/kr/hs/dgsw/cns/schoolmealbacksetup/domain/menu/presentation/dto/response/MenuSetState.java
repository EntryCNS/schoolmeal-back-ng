package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuSetState {
    private final MenuState state;
}
