package m3.common.commons;

import lombok.Getter;

@Getter
public class HttpExceptionError extends RuntimeException {

    private final ErrorCodes errorCode;

    public HttpExceptionError(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
