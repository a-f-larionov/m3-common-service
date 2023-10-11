package m3.common.commons;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    VALIDATE_ERROR(1, "Ошибка валидации");

    private final Integer code;
    private final String message;

    ErrorCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
