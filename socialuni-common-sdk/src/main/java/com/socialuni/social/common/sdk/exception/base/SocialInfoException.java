package com.socialuni.social.common.sdk.exception.base;


import com.socialuni.social.common.constant.ErrorType;

//正常业务错误，可以预见的
public abstract class SocialInfoException extends SocialException {
    public SocialInfoException(String errorMsg, Integer errorCode, String innerMsg) {
        super(errorMsg, errorCode, ErrorType.info, innerMsg);
    }


}