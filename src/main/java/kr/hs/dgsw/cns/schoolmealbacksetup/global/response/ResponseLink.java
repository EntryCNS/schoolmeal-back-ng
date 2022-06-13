package kr.hs.dgsw.cns.schoolmealbacksetup.global.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class ResponseLink {

    private String rel;
    private String method;
    private String href;

}
