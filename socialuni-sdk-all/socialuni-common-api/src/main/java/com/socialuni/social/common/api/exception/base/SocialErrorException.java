package com.socialuni.social.common.api.exception.base;


import com.socialuni.social.common.api.constant.ErrorType;

//最严重的级别错误，必须解决，或者找出原因
public abstract class SocialErrorException extends SocialException {
    public SocialErrorException(String errorMsg, Integer errorCode, String innerMsg) {
        super(errorMsg, errorCode, ErrorType.error, innerMsg);
    }
}
