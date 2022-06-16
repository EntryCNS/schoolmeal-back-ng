package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.type

import com.fasterxml.jackson.annotation.JsonCreator

enum class MenuCategory {
    KOREAN, WESTERN, JAPANESE, CHINESE;

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromMenuCategory(s: String) : MenuCategory {
            return valueOf(s.uppercase())
        }
    }
}