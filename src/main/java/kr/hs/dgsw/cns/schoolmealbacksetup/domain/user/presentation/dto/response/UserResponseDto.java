package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ResponseLink;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class UserResponseDto {

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("profile_image")
    private ResponseLink profileImage;

}
