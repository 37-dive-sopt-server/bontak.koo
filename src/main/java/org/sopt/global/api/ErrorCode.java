package org.sopt.global.api;

public enum ErrorCode {

    DUPLICATE_EMAIL("400", "이미 존재하는 이메일입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
