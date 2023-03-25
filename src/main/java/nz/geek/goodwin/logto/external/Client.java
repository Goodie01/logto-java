package nz.geek.goodwin.logto.external;

import nz.geek.goodwin.logto.domain.Scopes;
import nz.geek.goodwin.logto.domain.dto.CodeTokenResponse;
import nz.geek.goodwin.logto.domain.dto.SignInGenerationResponse;
import nz.geek.goodwin.logto.domain.dto.UserInfoResponse;
import nz.geek.goodwin.logto.internal.ClientImpl;
import nz.geek.goodwin.logto.internal.LogtoConfig;

import java.util.Set;

public interface Client {
    static ClientBuilder builder() {
        return new ClientBuilder();
    }

    static Client with(
            String endpoint,
            String appId,
            String appSecret) {
        LogtoConfig logtoConfig = new LogtoConfig(
            endpoint,
                appId,
                appSecret,
                Set.of(),
                Set.of(),
                ""
        );
        return new ClientImpl(logtoConfig);
    }

    SignInGenerationResponse signIn(String redirectUri,
                                    String state,
                                    Set<Scopes> scopes,
                                    Set<String> resources);

    CodeTokenResponse getToken(String redirectUri,
                               String code,
                               String codeVerifier);

    UserInfoResponse getUserInfo(CodeTokenResponse codeTokenResponse);
}
