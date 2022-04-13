package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpResponse {

    private String loginId;
    private String email;

}
