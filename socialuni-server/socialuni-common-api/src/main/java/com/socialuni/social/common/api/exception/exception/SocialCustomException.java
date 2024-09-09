package com.socialuni.social.common.api.exception.exception;


import com.qingchi.qing.common.exception.base.ErrorCode;
import com.qingchi.qing.common.exception.base.SocialInfoException;

public class SocialCustomException extends SocialInfoException {
    public SocialCustomException(String message) {
        //业务异常，返回给前端msg，内部消息记录msg自定义异常
        super(null, ErrorCode.CUSTOM_ERROR, message);
    }
}
