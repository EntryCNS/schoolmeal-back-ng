package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpResponse {

    @JsonProperty(value = "login_id")
    private String loginId;
    private String email;

}
