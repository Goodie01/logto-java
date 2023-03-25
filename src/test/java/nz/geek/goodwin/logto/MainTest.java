package nz.geek.goodwin.logto;

import io.javalin.Javalin;
import nz.geek.goodwin.logto.domain.dto.CodeTokenResponse;
import nz.geek.goodwin.logto.domain.dto.SignInGenerationResponse;
import nz.geek.goodwin.logto.domain.dto.UserInfoResponse;
import nz.geek.goodwin.logto.external.Client;
import nz.geek.goodwin.logto.internal.GenerateUtils;

import java.util.HashMap;
import java.util.Set;

class MainTest {
    public static void main(String[] args) {
        Client client = Client.builder()
                .setAppId("")
                .setAppSecret("")
                .setMainEndpoint("https://sohul4.logto.app/")
                .build();

        HashMap<String, String> stateMap = new HashMap<>();

        Javalin.create()
                .get("init", context -> {
                    String state = GenerateUtils.generateState();
                    SignInGenerationResponse signInResponse = client.signIn("http://localhost:7000/callback",
                            state,
                            Set.of(),
                            Set.of());

                    context.redirect(signInResponse.url());
                    stateMap.put(state, signInResponse.codeVerifier());
                })
                .get("callback", context -> {
                    String code = context.queryParam("code");
                    String state = context.queryParam("state");

                    String codeVerifier = stateMap.get(state);

                    CodeTokenResponse token = client.getToken(
                            "http://localhost:7000/callback",
                            code,
                            codeVerifier
                    );

                    UserInfoResponse userInfo = client.getUserInfo(token);

                    context.result(userInfo.sub());
                })
                .start(7000);
    }
}