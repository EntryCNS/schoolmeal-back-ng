package kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GoogleApiService {
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    class GetUserInfoResponse {
        @JsonProperty("id") String openId;
        @JsonProperty("email") String email;
        @JsonProperty("verified_email") Boolean isVerfiedEmail;
        @JsonProperty("name") String name;
        @JsonProperty("given_name") String givenName;
        @JsonProperty("picture") String profileImageUrl;
        @JsonProperty("locale") String locale;
    }

    @GET("/oauth2/v2/userinfo")
    Call<GetUserInfoResponse> getInfo(
            @Header("Authorization") String bearerToken
    );
}
