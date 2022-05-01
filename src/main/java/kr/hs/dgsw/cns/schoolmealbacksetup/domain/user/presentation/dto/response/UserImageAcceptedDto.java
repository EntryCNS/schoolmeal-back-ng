package kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import kr.hs.dgsw.cns.schoolmealbacksetup.global.response.ResponseLink;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ResponseStatus(HttpStatus.ACCEPTED)
public class UserImageAcceptedDto {

    public static UserImageAcceptedDto fromUser(User user) {
        List<ResponseLink> links = new ArrayList<>();
        String lnk = String.format("/users/%s/profile-image", user.getId());
        links.add(new ResponseLink("self", lnk));
        links.add(new ResponseLink("profile-image", lnk));

        return new UserImageAcceptedDto(links);
    }

    private List<ResponseLink> links;

}
