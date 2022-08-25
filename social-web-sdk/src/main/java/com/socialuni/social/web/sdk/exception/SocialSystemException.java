package com.socialuni.social.web.sdk.exception;

import com.socialuni.social.web.sdk.constant.ErrorCode;
import com.socialuni.social.web.sdk.exception.base.SocialErrorException;
import com.socialuni.social.web.sdk.constant.ErrorMsg;

//正常逻辑不会出现，想不到什么情况会出现，就是系统异常, 必须要解决，解决不了就是被攻击的问题。
public class SocialSystemException extends SocialErrorException {
    public SocialSystemException(String innerMsg) {
        //系统异常返回前端，消息内部记录
        super(ErrorMsg.systemErrorMsg, ErrorCode.SYSTEM_ERROR, innerMsg);
    }
}
