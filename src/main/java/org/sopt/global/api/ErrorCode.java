package org.sopt.global.api;

public enum ErrorCode {

    /**
     * 400 BAD REQUEST
     * */
    // 멤버 관련 예외
    DUPLICATE_EMAIL("40001", "이미 존재하는 이메일입니다."),
    MEMBER_UNDERAGE("40002", "20세 미만 회원은 가입할 수 없습니다."),


    /**
     * 500 INTERNAL SERVER ERROR
     * */
    // 파일 입출력 관련 예외
    FILE_SAVE_ERROR("50001", "파일 저장 중 오류가 발생했습니다."),
    FILE_LOAD_ERROR("50002", "파일을 불러오는 중 오류가 발생했습니다.");
    ;


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
