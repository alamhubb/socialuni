package com.socialuni.social.common.sdk.constant.platform;

/**
 * @author qinkaiyuan
 * @date 2019-06-22 22:23
 */

//什么算是公共常量，就是放到任何项目都可以用的
public class QQUrl {
    public static final String qq_mp_unionId_url = "https://api.q.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";

    public static final String qq_app_unionId_url = "https://graph.qq.com/oauth2.0/me?access_token={0}&unionid=1&fmt=json";
}
