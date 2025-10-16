package org.sopt.global.api.handler;

import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.GeneralException;

public class FileException extends GeneralException {

    public FileException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FileException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
