package com.socialuni.social.web.sdk.exception;


import com.socialuni.social.web.sdk.constant.ErrorCode;
import com.socialuni.social.web.sdk.exception.base.SocialInfoException;

public class SocialCustomException extends SocialInfoException {
    public SocialCustomException(String message) {
        //业务异常，返回给前端msg，内部消息记录msg自定义异常
        super(null, ErrorCode.CUSTOM_ERROR, message);
    }
}