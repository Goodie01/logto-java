package nz.geek.goodwin.logto.domain.exceptions;

import nz.geek.goodwin.logto.domain.dto.ErrorResponse;

public class LogtoJavaException extends RuntimeException {
    public LogtoJavaException() {
        super();
    }

    public LogtoJavaException(String message) {
        super(message);
    }

    public LogtoJavaException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogtoJavaException(Throwable cause) {
        super(cause);
    }

    protected LogtoJavaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
