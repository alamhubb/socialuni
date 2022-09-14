package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.constant.socialuni.GenderType;
import lombok.Data;

import java.util.List;

@Data
public class SocialuniAppConfigBO {
    //首页的tab栏目
    private String followTabName;
    private String homeTabName;
    private String cityTabName;
    private String selfSchoolTabName;
    private List<String> tabNames;

    //配置1，是否成年认证才可以发表自拍
    private Boolean talkImgNeedAdultAuth = false;
    //配置2，是否填写校园才可以发帖
    private Boolean hasSchoolCanPostTalk = false;
    //配置3，应用性别
    private String appGender = GenderType.all;

    //广告展示数量间隔
    private Integer talkShowAdIntervalKey = 8;
    //动态页广告展示次数
    private Integer talkShowAdCount = 10;
    //用户可以获取几次验证码
    private Integer authCodeCount = 30;
    //用户IP可以获取几次验证码
    private Integer authCodeIpCount = 200;
    //手机号可以获取几次验证码
    private Integer authCodePhoneCount = 30;
    //验证码有效时间多少分
    private Integer authCodeValidMinute = 30;
    //验证码间隔多少秒
    private Integer authCodeInterval = 120;
    //限制每天高质量举报次数
    private Integer highLimitReportCount = 20;
    //被举报几次隐藏
    private Integer reportCountHide = 1;
    //客服微信
    private String serviceWeChat;
    //系统异常错误消息
    private String errorMsg601UnLogin = "未登录，请进行登录";
    private String errorMsg604SystemError = "系统异常，有任何疑问请联系客服";
    private String errorMsg605UserBaned = "账号违规，被封禁，有任何疑问请联系客服";
}
