package nz.geek.goodwin.logto.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record OidcConfigResponse(
        @JsonProperty("authorization_endpoint") String authorizationEndpoint,
        @JsonProperty("claims_parameter_supported") boolean claimsParameterSupported,
        @JsonProperty("claims_supported") Set<String> claimsSupported,
        @JsonProperty("code_challenge_methods_supported") Set<String> codeChallengeMethodsSupported,
        @JsonProperty("end_session_endpoint") String endSessionEndpoint,
        @JsonProperty("grant_types_supported") Set<String> grantTypesSupported,
        @JsonProperty("issuer") String issuer,
        @JsonProperty("jwks_uri") String jwksUri,
        @JsonProperty("authorization_response_iss_parameter_supported") boolean authorizationResponseIssParameterSupported,
        @JsonProperty("response_modes_supported") Set<String> responseModesSupported,
        @JsonProperty("response_types_supported") Set<String> responseTypesSupported,
        @JsonProperty("scopes_supported") Set<String> scopesSupported,
        @JsonProperty("subject_types_supported") Set<String> subjectTypesSupported,
        @JsonProperty("token_endpoint_auth_methods_supported") Set<String> tokenEndpointAuthMethodsSupported,
        @JsonProperty("token_endpoint_auth_signing_alg_values_supported") Set<String> tokenEndpointAuthSigningAlgValuesSupported,
        @JsonProperty("token_endpoint") String tokenEndpoint,
        @JsonProperty("id_token_signing_alg_values_supported") Set<String> idTokenSigningAlgValuesSupported,
        @JsonProperty("pushed_authorization_request_endpoint") String pushedAuthorizationRequestEndpoint,
        @JsonProperty("request_parameter_supported") boolean requestParameterSupported,
        @JsonProperty("request_uri_parameter_supported") boolean requestUriParameterSupported,
        @JsonProperty("userinfo_endpoint") String userinfoEndpoint,
        @JsonProperty("revocation_endpoint") String revocationEndpoint,
        @JsonProperty("claim_types_supported") Set<String> claimTypesSupported
) {
}
