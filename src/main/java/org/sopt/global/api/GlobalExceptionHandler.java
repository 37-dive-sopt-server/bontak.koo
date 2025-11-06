package org.sopt.global.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.fail(errorCode.getCode(), errorCode.getMsg());

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

     // 존재하지 않는 URL 요청 → 404 Not Found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NoHandlerFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(HttpStatus.NOT_FOUND.value(), "요청하신 URL을 찾을 수 없습니다."));
    }


     // 지원하지 않는 HTTP 메서드 요청 → 405 Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), "허용되지 않은 HTTP 메서드입니다."));
    }

    // 서버 내부 오류 → 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnhandledException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부 오류가 발생했습니다."));
    }
}
