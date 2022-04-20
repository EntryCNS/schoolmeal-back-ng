package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class OAuthResponseDto {

    @NotNull
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
}
