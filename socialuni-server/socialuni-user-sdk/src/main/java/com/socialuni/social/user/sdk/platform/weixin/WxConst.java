package com.socialuni.social.user.sdk.platform.weixin;


import com.socialuni.social.user.sdk.platform.weixin.token.WxTokenResult;

/**
 * @author qinkaiyuan
 * @date 2019-06-22 22:23
 */

//什么算是公共常量，就是放到任何项目都可以用的
public class WxConst {
    public static final String wx_pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String wx_pay_result_notify_url = "https://api.socialuni.cn/socialuni/callback/wxPayNotify";

    public static final String mp_pay_trade_type = "JSAPI";
    public static final String app_pay_trade_type = "APP";

    private static WxTokenResult wxToken = new WxTokenResult();

    public static String wxTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";

    public static String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session?";
    public static String wx_msg_sec_check_url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=";
    public static String wx_img_sec_check_url = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=";
    public static String push_msg_url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";

    //talk被评论
    public static String talk_template_id = "mLMGMJWfz5dJVVclHIFCacD4hdMmIAly60TpQ1NF6hc";
    //评论回复
    public static String comment_template_id = "-lNlHFDVTCFM53t-UnDa6GIqdm_JsCrQbuj7VW9PwfM";

    //旧版本，新版本使用审核结果通知
//    public static String report_result_template_id = "J-lumGA7qklquvFXJSQ6o8imJC99MPIs0plFwA3ULgM";
    public static String report_result_template_id = "uiNkA0C5mmt_edJvDEXzVhr8Fe_T2VYAafoM_Vu04iM";

    //违规通知
    public static String violation_template_id = "sXNJQqr8qLmkrc2bag94vKuNs5IGRDjHU_fuIqN7pW4";

    //后台配置，哪个场景，使用哪些模板列表
    //用户发表动态时，接受评论，接收评论回复，接受违规处理
    public static String[] talkTemplateIds = {WxConst.talk_template_id, WxConst.comment_template_id, WxConst.violation_template_id};
    //用户发表评论时，接受评论被回复，接受违规处理
    public static String[] commentTemplateIds = {WxConst.comment_template_id, WxConst.violation_template_id};
    //评论发表msg时，接受违规被封禁处理
    public static String[] messageTemplateIds = {WxConst.violation_template_id};
    //用户举报人时，接收举报处理结果
    public static String[] reportTemplateIds = {report_result_template_id};

    public static WxTokenResult getWxToken() {
        return wxToken;
    }

    public static void setWxToken(WxTokenResult wxToken) {
        WxConst.wxToken = wxToken;
    }
}
