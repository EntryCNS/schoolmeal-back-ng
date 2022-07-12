package kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import retrofit2.Call;
import retrofit2.http.*;

public interface GoogleAuthService {
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    class GetAccessTokenResponse {
        @JsonProperty("access_token") String accessToken;
        @JsonProperty("refresh_token") String refreshToken;
        @JsonProperty("expires_in") Integer expiresIn;
        @JsonProperty("scope") String scope;
        @JsonProperty("token_type") String tokenType;
        @JsonProperty("id_token") String idToken;
    }

    @FormUrlEncoded
    @POST("token")
    Call<GetAccessTokenResponse> getAccessToken(
            @Field("code") String code,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("redirect_uri") String redirectUri
    );
}
