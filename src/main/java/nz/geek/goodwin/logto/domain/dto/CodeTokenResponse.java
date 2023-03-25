package nz.geek.goodwin.logto.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CodeTokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in") String expiresIn,
        @JsonProperty("id_token") String idToken,
        @JsonProperty("scope") String scope,
        @JsonProperty("token_type") String tokenType
) {
}
