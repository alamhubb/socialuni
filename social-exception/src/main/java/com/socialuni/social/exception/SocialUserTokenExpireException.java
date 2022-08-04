package com.socialuni.social.exception;

import com.socialuni.social.exception.base.SocialWarnException;
import com.socialuni.social.exception.constant.ErrorCode;

public class SocialUserTokenExpireException extends SocialWarnException {
    //用户未登录访问了需要登录的接口
    public SocialUserTokenExpireException() {
        super("用户凭证过期", ErrorCode.NOT_LOGGED_ERROR, "用户凭证过期");
    }
}
