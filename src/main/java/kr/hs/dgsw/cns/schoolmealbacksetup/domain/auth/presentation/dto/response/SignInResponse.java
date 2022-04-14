package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    @JsonProperty("refresh_token")
    private String refreshToken;


}
