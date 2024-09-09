package com.socialuni.social.common.api.exception.exception;

public class SocialNullUserException extends SocialParamsException {
    //这个是异常逻辑，比如不存在userId啥的
    public SocialNullUserException() {
        //用户为空异常
        super("用户为空");
    }
}
