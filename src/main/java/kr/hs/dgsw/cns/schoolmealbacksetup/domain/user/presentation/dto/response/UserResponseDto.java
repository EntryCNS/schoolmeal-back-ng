package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    @NotNull
    private String name;

    @NotNull
    @JsonProperty(value = "login_id")
    private String loginId;

    @NotNull
    private String email;

    @NotNull
    private String image;

}
