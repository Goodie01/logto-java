package nz.geek.goodwin.logto.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInfoResponse(
    @JsonProperty("sub") String sub,

    // Scope `profile`
    @JsonProperty("name") String name,
    @JsonProperty("picture") String picture,
    @JsonProperty("username") String username,

    // Scope `email`
    String email,
    String emailVerified,

    // Scope `phone`
    String phoneNumber,
    String phoneNumberVerified
) {
}


//{"sub":"OxjttgoEky45","name":null,"picture":null,"username":null}
