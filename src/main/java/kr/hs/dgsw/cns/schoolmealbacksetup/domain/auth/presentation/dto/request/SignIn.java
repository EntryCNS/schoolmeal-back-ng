package kr.hs.dgsw.cns.schoolmealbacksetup.domain.auth.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter @Setter
@AllArgsConstructor
public class SignIn {

    @NotNull
    @Size(min=3, max=20)
    private String id;

    @NotNull
    @Size(min=4, max=20)
    private String password;



}
