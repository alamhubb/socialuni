package com.socialuni.social.common.api.exception.base;


import com.socialuni.social.common.api.constant.ErrorType;

//告警的级别错误，知晓就行，无法解决，要警惕
public abstract class SocialWarnException extends SocialException {
    public SocialWarnException(String errorMsg, Integer errorCode, String innerMsg) {
        super(errorMsg, errorCode, ErrorType.warn, innerMsg);
    }
}
