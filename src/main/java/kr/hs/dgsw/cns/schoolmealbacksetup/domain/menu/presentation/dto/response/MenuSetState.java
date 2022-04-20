package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MenuSetState {

    private final Long menuId;

    private final MenuState state;
}
