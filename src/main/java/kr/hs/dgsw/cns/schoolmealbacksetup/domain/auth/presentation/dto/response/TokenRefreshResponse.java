package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenRefreshResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
