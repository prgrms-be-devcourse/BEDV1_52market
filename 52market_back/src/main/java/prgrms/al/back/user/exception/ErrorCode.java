package prgrms.al.back.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    SERVER_ERROR(404,"C002", "NOT FOUND"),
    ACCESS_DENIED(403, "C003", "Access is Denied"),

    NICKNAME_DUPLICATION(400, "U001", "NickName is Duplication"),
    NAME_DUPLICATION(400, "U002", "Name is Duplication"),
    NOT_FOUND_USER(400, "U003", "User is not exists"),
    LOGIN_INPUT_INVALID(400, "U004", "Login input is invalid"),
    LOCATION_INPUT_INVALID(400, "U005", "Location input is invalid"),
    PASSWORD_INPUT_INVALID(400, "U006", "Password input is invalid")
    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
