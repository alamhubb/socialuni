package com.socialuni.social.common.api.exception.base;

import lombok.Getter;

@Getter
public abstract class SocialException extends RuntimeException {
    private final String errorType;
    private final String errorMsg;
    private final Integer errorCode;
    private final String innerMsg;

    public SocialException(String errorMsg, Integer errorCode, String errorType, String innerMsg) {
        super(innerMsg);
        this.errorMsg = errorMsg;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.innerMsg = innerMsg;
    }

    /*public SocialException(String errorMsg, Integer errorCode, String errorType) {
        this(errorMsg, errorCode, errorType, errorMsg);
    }*/
}
