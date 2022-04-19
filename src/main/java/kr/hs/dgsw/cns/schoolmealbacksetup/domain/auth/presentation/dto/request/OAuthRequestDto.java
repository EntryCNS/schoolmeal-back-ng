package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class OAuthRequestDto {

    @NotNull
    private String code;
}
