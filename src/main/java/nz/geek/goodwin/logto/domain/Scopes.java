package nz.geek.goodwin.logto.domain;

public enum Scopes {
    OPENID("openid"),
    OFFLINE_ACCESS("offline_access"),
    PROFILE("profile"),
    EMAIL("email"),
    PHONE("phone"),
    CUSTOM_DATA("custom_data"),
    IDENTITIES("identities"),
    ;

    private final String identifier;

    Scopes(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
