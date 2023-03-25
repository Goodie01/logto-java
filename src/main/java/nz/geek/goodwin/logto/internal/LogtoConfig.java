package nz.geek.goodwin.logto.internal;

import nz.geek.goodwin.logto.domain.Scopes;

import java.net.URI;
import java.util.List;
import java.util.Set;

public record LogtoConfig(
    String appId,
    String appSecret,
    String endpoint,
    Set<Scopes> scopes,
    Set<String> resources,
    String prompt
) {
}
