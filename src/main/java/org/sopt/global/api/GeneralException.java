package org.sopt.global.api;

public class GeneralException extends RuntimeException {

  private final ErrorCode errorCode;

  public GeneralException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public GeneralException(ErrorCode errorCode, String detail) {
    super(errorCode.getMessage() + " (" + detail + ")");
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
