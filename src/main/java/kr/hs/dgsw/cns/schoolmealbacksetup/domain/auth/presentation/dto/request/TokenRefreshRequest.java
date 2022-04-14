package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class TokenRefreshRequest {
    @NotNull
    @JsonProperty("refresh_token")
    private String refreshToken;
}
