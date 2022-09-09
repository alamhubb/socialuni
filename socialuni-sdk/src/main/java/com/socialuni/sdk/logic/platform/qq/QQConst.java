package com.socialuni.sdk.logic.platform.qq;


import com.socialuni.sdk.logic.platform.weixin.token.WxTokenResult;

/**
 * @author qinkaiyuan
 * @date 2019-06-22 22:23
 */

//什么算是公共常量，就是放到任何项目都可以用的
public class QQConst {
    public static final String qq_pay_url = "https://qpay.qq.com/cgi-bin/pay/qpay_unified_order.cgi";
    public static final String qq_pay_result_notify_url = "https://api.socialuni.cn/user/qqPayNotify";

    public static final String qqLoginUrl = "https://api.q.qq.com/sns/jscode2session?";
    public static final String qq_msg_sec_check_url = "https://api.q.qq.com/api/json/security/MsgSecCheck?access_token=";
    public static final String qq_img_sec_check_url = "https://api.q.qq.com/api/json/security/ImgSecCheck?access_token=";

    public static final String qq_app_unionId_url = "https://graph.qq.com/oauth2.0/me?access_token={0}&unionid=1&fmt=json";

    public static final String form_push_msg_url = "https://api.q.qq.com/api/json/template/send?access_token=";
    public static final String push_msg_url = "https://api.q.qq.com/api/json/subscribe/SendSubscriptionMessage?access_token=";

    //获取unionId
    public static final String get_unionId_url = "https://graph.qq.com/oauth2.0/me?access_token=";

    //订阅通知
    public static final String talk_template_id = "1c53b12e6f6ad82f3fb0557b02eaf20b";
    public static final String comment_template_id = "46e57d8a10b67d8c9278788410933b61";


    public static final String report_result_template_id = "c39c7d30d130ed170c103363d9ca2fff";

    //违规删除，
    public static final String violation_template_id = "2128bec457f63527f91311f54da520b7";

    public static String qqTokenUrl = "https://api.q.qq.com/api/getToken?grant_type=client_credential&";

    private static WxTokenResult qqToken = new WxTokenResult();

    public static WxTokenResult getQQToken() {
        return qqToken;
    }

    public static void setQQToken(WxTokenResult qqToken) {
        QQConst.qqToken = qqToken;
    }
}
