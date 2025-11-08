package org.sopt.global.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /**
     * 400 BAD REQUEST
     * */
    // 멤버 관련 예외
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, 400, "이미 존재하는 이메일입니다."),
    MEMBER_UNDERAGE(HttpStatus.BAD_REQUEST, 400, "20세 미만 회원은 가입할 수 없습니다."),


    // 아티클 관련
    DUPLICATE_ARTICLE_TITLE(HttpStatus.BAD_REQUEST, 400, "이미 존재하는 아티클 제목입니다."),


    /**
     * 404 NOT FOUND
     */
    // 멤버 관련
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "멤버를 찾을 수 없습니다."),

    // 아티클 관련
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "아티클을 찾을 수 없습니다."),

    /**
     * 500 INTERNAL SERVER ERROR
     * */
    // 파일 입출력 관련 예외
    FILE_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "파일 저장 중 오류가 발생했습니다."),
    FILE_LOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "파일을 불러오는 중 오류가 발생했습니다.");
    ;


    private final HttpStatus status;
    private final int code;
    private final String msg;

    ErrorCode(HttpStatus status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }
}
