package nz.geek.goodwin.logto.internal;

import nz.geek.goodwin.logto.domain.Scopes;

import java.util.Set;

public final class Constants {
    public static final Set<Scopes> DEFAULT_SCOPES = Set.of(Scopes.OPENID, Scopes.OFFLINE_ACCESS, Scopes.PROFILE);
    public static final String SCOPE_OPENID = "openid";
    public static final String SCOPE_OFFLINE_ACCESS = "offline_access";
    public static final String SCOPE_PROFILE = "profile";
    public static final String SCOPE_EMAIL = "email";
    public static final String SCOPE_PHONE = "phone";
    public static final String SCOPE_CUSTOM_DATA = "custom_data";
    public static final String SCOPE_IDENTITIES = "identities";
    public static final String PROMPT_CONSENT = "consent";
    public static final String PROMPT_LOGIN = "login";
    public static final String AUTHORIZATION_CODE = "authorization_code";

    public static final String CODE_CHALLANGE_METHOD_S256 = "S256";
    public static final String RESPONSE_TYPE_CODE = "code";
    public final static String CLIENT_ID = "client_id";
    public final static String CLIENT_SECRET = "client_secret";
    public final static String CODE = "code";
    public final static String QUERY_PARAM_CODE_CHALLENGE = "code_challenge";
    public final static String QUERY_PARAM_CODE_CHALLENGE_METHOD = "code_challenge_method";
    public final static String CODE_VERIFIER = "code_verifier";
    public final static String ERROR = "error";
    public final static String ERROR_DESCRIPTION = "error_description";
    public final static String GRANT_TYPE = "grant_type";
    public final static String POST_LOGOUT_REDIRECT_URI = "post_logout_redirect_uri";
    public final static String PROMPT = "prompt";
    public final static String QUERY_PARAM_REDIRECT_URI = "redirect_uri";
    public final static String REFRESH_TOKEN = "refresh_token";
    public final static String RESOURCE = "resource";
    public final static String QUERY_PARAM_RESPONSE_TYPE = "response_type";
    public final static String QUERY_PARAM_SCOPE = "scope";
    public final static String QUERY_PARAM_STATE = "state";
    public final static String TOKEN = "token";

    private Constants() {

    }
}
