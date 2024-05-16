package com.socialuni.social.common.api.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
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
    //获取联系方式消耗多少金币, 100金币，1块钱
    private Integer contactExpenseShell = 100;
    //用户获得金币百分比
    private Integer contactUserReceiveShell = 50;
    //系统抽成比例，百分比
    private Integer sysServiceReceiveRatio = 20;
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
    private List<Integer> talkShowAdIndexList = this.computedTalkShowAdIndexList();
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

    private String qq_account = "491369310";
    private String wx_mp_id = "";
    private String wx_app_id = "";
    private String wx_mp_secret = "";
    private String wx_merchant_id = "";
    private String wx_merchant_key = "";
    private String devPublishDataApiUrl = "";
    //微信平台审核中
    private Boolean mp_wx_auditing = false;
    //qq平台审核中
    private Boolean mp_qq_auditing = false;

    private List<Integer> computedTalkShowAdIndexList() {
        Integer talkAdInterval = this.getTalkShowAdInterval();
        Integer talkShowAdCount = this.getTalkShowAdCount();
        if (talkAdInterval < 6) {
            log.warn("广告展示频率不能低于6");
        } else if (talkAdInterval > 30) {
            log.warn("广告展示频率不建议大于30，没有意义");
        }
        List<Integer> showAdList = new ArrayList<>();
        //7, 15, 27, 43, 63, 87, 111, 135, 159, 183
        //8 12 16 20 24 24 24 24 24
        //设置广告间隔
        showAdList.add(talkAdInterval - 1);
        for (int i = 0; i < talkShowAdCount - 1; i++) {
            //每次*2 7 15
            int a = talkAdInterval * (i + 2) - 1;
            if (i < 4) {
                //4 ,+一半的次数，叠加
                a = a + (((i + 1) * i) / 2) * talkAdInterval / 2;
            } else {
                a = a + (4 * (i - 1) - 2) * talkAdInterval / 2;
            }
            showAdList.add(a);
        }
        return showAdList;
    }
}
