package com.socialuni.social.exception;

import com.socialuni.social.constant.ErrorCode;
import com.socialuni.social.exception.base.SocialWarnException;

public class SocialNotLoginException extends SocialWarnException {
    //用户未登录访问了需要登录的接口
    public SocialNotLoginException() {
        super("请进行登录", ErrorCode.NOT_LOGGED_ERROR, "无用户访问需用户接口");
    }
}
