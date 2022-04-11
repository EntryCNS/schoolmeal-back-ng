package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class SignIn {

    private String id;
    private String password;

}