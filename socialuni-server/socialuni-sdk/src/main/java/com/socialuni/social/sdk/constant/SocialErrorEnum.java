package com.socialuni.social.sdk.constant;


public class SocialErrorEnum {
    //请求成功
    public static final Integer success = 0;
    //未登录
    public static final Integer NOT_LOGGED_ERROR = 601;
    //业务错误
    public static final Integer BUSINESS_ERROR = 602;
    //自定义错误
    public static final Integer CUSTOM_ERROR = 603;
    //系统异常
    public static final Integer SYSTEM_ERROR = 604;
    //封号异常
    public static final Integer BANNED_ERROR = 605;
    //
    public static final Integer IP_LIMIT_ERROR = 606;

    //未登录
    public static final SocialNumEnum NOT_LOGGED_ERROR_ENUM = new SocialNumEnum(SocialErrorEnum.NOT_LOGGED_ERROR, "请进行登录");
    //业务错误
    public static final SocialNumEnum BUSINESS_ERROR_ENUM = new SocialNumEnum(SocialErrorEnum.BUSINESS_ERROR, "请进行登录");
    //自定义错误
    public static final SocialNumEnum CUSTOM_ERROR_ENUM = new SocialNumEnum(SocialErrorEnum.CUSTOM_ERROR, "请进行登录");
    //系统异常
    public static final SocialNumEnum SYSTEM_ERROR_ENUM = new SocialNumEnum(SocialErrorEnum.SYSTEM_ERROR, "请进行登录");
    //封号异常
    public static final SocialNumEnum BANNED_ERROR_ENUM = new SocialNumEnum(SocialErrorEnum.BANNED_ERROR, "请进行登录");
    //
    public static final SocialNumEnum IP_LIMIT_ERROR_ENUM = new SocialNumEnum(SocialErrorEnum.IP_LIMIT_ERROR, "请进行登录");

}
