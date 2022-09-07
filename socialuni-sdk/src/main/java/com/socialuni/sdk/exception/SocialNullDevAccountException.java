package com.socialuni.sdk.exception;

import com.socialuni.social.web.sdk.constant.ErrorCode;
import com.socialuni.social.web.sdk.exception.base.SocialWarnException;

public class SocialNullDevAccountException extends SocialWarnException {
    //用户未登录访问了需要登录的接口
    public SocialNullDevAccountException() {
        super("请填写开发者秘钥", ErrorCode.NULL_DEV_ERROR, "无开发者秘钥访问接口");
    }
}
