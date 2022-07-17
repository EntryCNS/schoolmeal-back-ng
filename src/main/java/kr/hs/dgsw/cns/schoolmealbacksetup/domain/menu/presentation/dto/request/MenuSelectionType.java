package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.request;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type.MenuState;

public enum MenuSelectionType {
    ACCEPTED, ACCEPTED_TODAY, DENIED, STANDBY;

    public static MenuSelectionType fromString(String name) {
        return MenuSelectionType.valueOf(name);
    }

    public MenuState toEntityType() {
        return MenuState.valueOf(this.name());
    }
}
