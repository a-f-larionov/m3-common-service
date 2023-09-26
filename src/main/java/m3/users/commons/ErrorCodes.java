package m3.users.commons;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    VALIDATE_ERROR(1, "Ошибка валидации");

    private Integer code;
    private String message;

    ErrorCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
