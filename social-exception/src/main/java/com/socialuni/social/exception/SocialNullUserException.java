package com.socialuni.social.exception;

public class SocialNullUserException extends SocialParamsException {
    public SocialNullUserException() {
        //用户为空异常
        super("用户为空");
    }
}
