package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    @NotNull
    @JsonProperty(value = "user_id")
    private int userId;

}
