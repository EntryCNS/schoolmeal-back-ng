package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class SignUpRequest {

    @NotNull
    @Size(min = 5, max = 100)
    private String loginId;

    @NotNull
    @Size(min = 5, max = 128)
    private String email;

    @NotNull
    @Size(min=5, max=256)
    private String password;

}
