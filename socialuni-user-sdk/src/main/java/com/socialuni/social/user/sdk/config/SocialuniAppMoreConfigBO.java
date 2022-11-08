package com.socialuni.social.user.sdk.config;

import lombok.Data;

import java.util.List;

@Data
public class SocialuniAppMoreConfigBO {
    //客服微信
    private String serviceWeChat;
    // 会员价格
    private Integer vipPrice = 1000;
    // 首页swiper高度
    private Integer swiperHeight = 130;
    // 官网首页url
    private String homeUrl = "https://www.socialuni.cn/";
    // 建议页url
    private String suggestUrl = "https://www.socialuni.cn/qingchi/suggest";
    //联系我们页url
    private String contactUsUrl = "https://www.socialuni.cn/qingchi/contact";
    //用户协议页url
    private String userAgreementUrl = "https://www.socialuni.cn/qingchi/agreement";
    //用户隐私页url
    private String userPrivacyUrl = "https://www.socialuni.cn/qingchi/privacy";
    //儿童保护协议页url
    private String childProtectUrl = "https://www.socialuni.cn/qingchi/childProtect";
    //激励视频限制次数页url
    private Integer rewardedAdLimit = 3;
    //动态缓存数量
//    private Integer talkCacheNum = 4;
    //获取联系方式消耗多少贝壳
    private Integer contactExpenseShell = 9999;
    //用户获得贝壳百分比
    private Integer contactUserReceiveShell = 50;
    //系统异常错误消息
    private String errorMsgContactService = "有任何疑问请联系客服";
    private String errorMsg601UnLogin = "未登录，请进行登录，是否前往登录";
    private String errorMsg604SystemError = "系统异常，有任何疑问请联系客服";
//    private String errorMsg605UserBaned = "账号违规，被封禁，有任何疑问请联系客服";

    //验证码间隔多少秒
    private Integer authCodeInterval = 120;
    //被举报几次隐藏
    private Integer reportCountHide = 1;
    private Boolean showSwipers = true;

    //广告展示数量间隔
    private Integer talkShowAdInterval = 8;
    //动态页广告展示次数
    private Integer talkShowAdCount = 10;
    //广告展示，自动计算根据上述内容
    private List<Integer> talkShowAdIndexList;
    //用户可以获取几次验证码
    private Integer authCodeCount = 30;
    //用户IP可以获取几次验证码
    private Integer authCodeIpCount = 200;
    //手机号可以获取几次验证码
    private Integer authCodePhoneCount = 30;
    //验证码有效时间多少分
    private Integer authCodeValidMinute = 30;
    //限制每天高质量举报次数
    private Integer highLimitReportCount = 20;
}
