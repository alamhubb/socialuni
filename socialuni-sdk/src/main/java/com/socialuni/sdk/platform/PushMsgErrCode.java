package com.socialuni.sdk.platform;


import java.util.Arrays;
import java.util.List;

public class PushMsgErrCode {
    //token过期
    public static final Integer token_expired = 40014;
    //用户未订阅
    public static final Integer user_unsubscribe = 46001;
    //当日超过推送限额
    public static final Integer limit_num = 46002;
    //过期
    public static final Integer expired = 41028;
    //失效
    public static final Integer lose = 41029;

    public static final List<Integer> retryCodes = Arrays.asList(user_unsubscribe, limit_num, expired, lose);
}
