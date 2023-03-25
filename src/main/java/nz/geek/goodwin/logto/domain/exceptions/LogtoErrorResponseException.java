package nz.geek.goodwin.logto.domain.exceptions;

import nz.geek.goodwin.logto.domain.dto.ErrorResponse;

public class LogtoErrorResponseException extends LogtoJavaException{
    private final ErrorResponse errorResponse;

    public LogtoErrorResponseException(final ErrorResponse errorResponse) {
        super("Logto error encountered, error: " + errorResponse.error() + "; Description: " + errorResponse.errorDescription());
        this.errorResponse = errorResponse;
    }
}
