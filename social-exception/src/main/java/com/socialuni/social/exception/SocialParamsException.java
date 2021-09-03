package com.socialuni.social.exception;

import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.exception.base.SocialWarnException;

//逻辑可能有问题，或者被突破，预见了但不应该
public class SocialParamsException extends SocialWarnException {
    //用户未登录访问了需要登录的接口
    public SocialParamsException(String errorMsg) {
        super(errorMsg, ErrorCode.PARAMS_ERROR, "异常访问");
    }
}
