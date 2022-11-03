package com.socialuni.social.common.sdk.exception.exception;

import com.socialuni.social.common.sdk.constant.ErrorCode;
import com.socialuni.social.common.sdk.exception.base.SocialWarnException;

public class SocialUserTokenExpireException extends SocialWarnException {
    //用户未登录访问了需要登录的接口
    public SocialUserTokenExpireException() {
        super("用户凭证过期", ErrorCode.NOT_LOGGED_ERROR, "用户凭证过期");
    }
}
