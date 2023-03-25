package nz.geek.goodwin.logto.domain.dto;

public record SignInGenerationResponse(
        String url,
        String codeVerifier
) {
}
