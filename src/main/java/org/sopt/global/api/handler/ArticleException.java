package org.sopt.global.api.handler;

import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.GeneralException;

public class ArticleException extends GeneralException {
    public ArticleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
