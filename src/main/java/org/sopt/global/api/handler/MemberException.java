package org.sopt.global.api.handler;

import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
