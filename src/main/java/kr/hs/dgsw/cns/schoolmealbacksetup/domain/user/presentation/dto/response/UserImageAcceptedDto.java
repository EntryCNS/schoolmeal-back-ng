package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ResponseLink;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ResponseStatus(HttpStatus.ACCEPTED)
public class UserImageAcceptedDto {
    private List<ResponseLink> links;

    public static UserImageAcceptedDto fromUser(User user) {
        List<ResponseLink> links = new ArrayList<>();
        StringBuilder linkBuilder = new StringBuilder(String.format("/users/%s", user.getId()));

        links.add(new ResponseLink("self", HttpMethod.GET.toString(),
                linkBuilder.toString()));
        links.add(new ResponseLink("profile-image", HttpMethod.GET.toString(),
                linkBuilder.append("/profile-image").toString()));

        return new UserImageAcceptedDto(links);
    }
}