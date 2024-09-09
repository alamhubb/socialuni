package com.socialuni.social.common.api.exception.exception;


import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.exception.base.SocialInfoException;

public class SocialBusinessException extends SocialInfoException {
    public SocialBusinessException(String message) {
        //业务异常，返回给前端msg，内部消息记录msg业务异常，预见了且正常业务
        super(message, ErrorCode.BUSINESS_ERROR, message);
    }
}
