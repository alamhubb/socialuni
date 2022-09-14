package com.socialuni.sdk.config;

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

    //系统异常错误消息
//    private String errorMsg601UnLogin = "未登录，请进行登录";
//    private String errorMsg604SystemError = "系统异常，有任何疑问请联系客服";
//    private String errorMsg605UserBaned = "账号违规，被封禁，有任何疑问请联系客服";
}
