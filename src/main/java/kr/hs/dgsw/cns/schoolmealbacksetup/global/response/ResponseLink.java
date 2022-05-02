package kr.hs.dgsw.cns.schoolmealbacksetup.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
@AllArgsConstructor
public class ResponseLink {

    private String rel;
    private String method;
    private String href;

}
