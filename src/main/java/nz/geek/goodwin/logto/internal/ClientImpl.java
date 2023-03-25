package nz.geek.goodwin.logto.internal;

import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import nz.geek.goodwin.logto.external.Client;
import nz.geek.goodwin.logto.domain.Scopes;
import nz.geek.goodwin.logto.domain.dto.SignInGenerationResponse;
import nz.geek.goodwin.logto.domain.exceptions.LogtoErrorResponseException;
import nz.geek.goodwin.logto.domain.dto.CodeTokenResponse;
import nz.geek.goodwin.logto.domain.dto.ErrorResponse;
import nz.geek.goodwin.logto.domain.dto.OidcConfigResponse;
import nz.geek.goodwin.logto.domain.dto.UserInfoResponse;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientImpl implements Client {
    private final LogtoConfig config;
    private OidcConfigResponse oidcConfig = null;

    public ClientImpl(LogtoConfig config) {
        this.config = config;
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
    }

    private OidcConfigResponse getOidcConfig() {
        if (this.oidcConfig != null) {
            return this.oidcConfig;
        }

        OidcConfigResponse oidcConfigResponse = Unirest.get(config.endpoint() + "oidc/.well-known/openid-configuration")
                .asObject(OidcConfigResponse.class)
                .getBody();
        this.oidcConfig = oidcConfigResponse;
        return oidcConfigResponse;
    }

    @Override
    public SignInGenerationResponse signIn(final String redirectUri,
                                           final String state,
                                           final Set<Scopes> scopes,
                                           final Set<String> resources) {
        if (Objects.equals(redirectUri, "")) {
            throw new IllegalArgumentException("Invalid redirect URI");
        }

        final OidcConfigResponse oidcConfig = getOidcConfig();
        final String codeVerifier = GenerateUtils.generateCodeVerifier();
        final String requestScopesString = generateScopeRequestString(scopes);

        String url = oidcConfig.authorizationEndpoint() + "?" + Constants.CLIENT_ID + "=" + config.appId()
                + "&" + Constants.QUERY_PARAM_CODE_CHALLENGE + "=" + GenerateUtils.generateCodeChallenge(codeVerifier)
                + "&" + Constants.QUERY_PARAM_CODE_CHALLENGE_METHOD + "=" + Constants.CODE_CHALLANGE_METHOD_S256
                + "&" + Constants.QUERY_PARAM_STATE + "=" + state
                + "&" + Constants.QUERY_PARAM_REDIRECT_URI + "=" + redirectUri
                + "&" + Constants.QUERY_PARAM_RESPONSE_TYPE + "=" + Constants.CODE
                + "&" + Constants.QUERY_PARAM_SCOPE + "=" + requestScopesString;

        url = url + resources.stream()
                .map(s1 -> "&" + Constants.RESOURCE + "=" + s1)
                .collect(Collectors.joining());

        url = url + "&" + Constants.PROMPT + "=" + (config.prompt() != null ? config.prompt() : Constants.PROMPT_CONSENT);

        return new SignInGenerationResponse(url, codeVerifier);
    }

    private String generateScopeRequestString(Set<Scopes> scopes) {
        final Set<Scopes> requestScopes = new HashSet<>();
        requestScopes.addAll(scopes);
        requestScopes.addAll(config.scopes());
        requestScopes.addAll(Constants.DEFAULT_SCOPES);

        return requestScopes.stream()
                .map(Scopes::getIdentifier)
                .collect(Collectors.joining("+"));
    }

    @Override
    public CodeTokenResponse getToken(final String redirectUri,
                                      final String code,
                                      final String codeVerifier) {
        OidcConfigResponse oidcConfig = getOidcConfig();

        String body = Constants.CLIENT_ID + "=" + config.appId()
                + "&" + Constants.CLIENT_SECRET + "=" + config.appSecret()
                + "&" + Constants.CODE + "=" + code
                + "&" + Constants.CODE_VERIFIER + "=" + codeVerifier
                + "&" + Constants.QUERY_PARAM_REDIRECT_URI + "=" + redirectUri
                + "&" + Constants.GRANT_TYPE + "=" + Constants.AUTHORIZATION_CODE;

        return Unirest.post(oidcConfig.tokenEndpoint())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(body)
                .asObject(CodeTokenResponse.class)
                .ifFailure(ErrorResponse.class, errorResponse -> {
                    throw new LogtoErrorResponseException(errorResponse.getBody());
                })
                .getBody();
    }

    @Override
    public UserInfoResponse getUserInfo(final CodeTokenResponse codeTokenResponse) {
        OidcConfigResponse oidcConfig = getOidcConfig();

        return Unirest.get(oidcConfig.userinfoEndpoint())
                .header("Authorization", "Bearer " + codeTokenResponse.accessToken())
                .asObject(UserInfoResponse.class)
                .ifFailure(ErrorResponse.class, errorResponse -> {
                    throw new LogtoErrorResponseException(errorResponse.getBody());
                })
                .getBody();
    }
}
