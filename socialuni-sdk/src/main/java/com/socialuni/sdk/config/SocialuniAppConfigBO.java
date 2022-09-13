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
    private Boolean needAdultCanPostPeoplePhoto = false;
    //配置2，是否填写校园才可以发帖
    private Boolean hasSchoolCanPostTalk = false;
    //配置3，应用性别
    private String appGender = GenderType.all;
}
