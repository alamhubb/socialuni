package com.socialuni.social.common.api.constant;


public class ErrorCode {
    //请求成功  0  resultRO.successCode
//    public static final Integer success = 0;
    //未登录
    public static final Integer NOT_LOGGED_ERROR = 601;
    //业务错误,一定会出现
    public static final Integer BUSINESS_ERROR = 602;
    //自定义错误
    public static final Integer CUSTOM_ERROR = 603;
    //必须解决和搞明白为什么的
    public static final Integer SYSTEM_ERROR = 604;
    //封号异常
    public static final Integer BANNED_ERROR = 605;
    //
    public static final Integer IP_LIMIT_ERROR = 606;
    //非法访问，正常程序执行不会出现,可以预料
    public static final Integer PARAMS_ERROR = 607;
    public static final Integer NULL_DEV_ERROR = 608;
}
