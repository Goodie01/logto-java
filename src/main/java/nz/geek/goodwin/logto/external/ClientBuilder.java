package nz.geek.goodwin.logto.external;

import nz.geek.goodwin.logto.domain.Scopes;
import nz.geek.goodwin.logto.domain.exceptions.LogtoJavaException;
import nz.geek.goodwin.logto.internal.ClientImpl;
import nz.geek.goodwin.logto.internal.LogtoConfig;

import java.util.Set;

public class ClientBuilder {
    private String appId;
    private String appSecret;
    private String mainEndpoint;
    private Set<Scopes> scopes;
    private Set<String> resources;
    private String prompt;

    ClientBuilder() {
    }

    public Client build() {
        if(appId == null || appId.equals("")) {
            throw new LogtoJavaException("No App ID provided");
        }

        if(mainEndpoint == null || mainEndpoint.equals("")) {
            throw new LogtoJavaException("No endpoint provided");
        }

        if(scopes == null) {
            scopes = Set.of();
        }

        if(resources == null) {
            resources = Set.of();
        }

        return new ClientImpl(new LogtoConfig(
                appId,
                appSecret,
                mainEndpoint,
                scopes,
                resources,
                prompt
        ));
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getMainEndpoint() {
        return mainEndpoint;
    }

    public Set<Scopes> getScopes() {
        return scopes;
    }

    public Set<String> getResources() {
        return resources;
    }

    public ClientBuilder setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public ClientBuilder setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public ClientBuilder setMainEndpoint(String mainEndpoint) {
        this.mainEndpoint = mainEndpoint;
        return this;
    }

    public ClientBuilder setScopes(Set<Scopes> scopes) {
        this.scopes = scopes;
        return this;
    }

    public ClientBuilder setResources(Set<String> resources) {
        this.resources = resources;
        return this;
    }

    public ClientBuilder setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public String getPrompt() {
        return prompt;
    }
}
