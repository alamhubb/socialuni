package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.config.SocialuniAppType;
import lombok.Data;

import java.util.List;

@Data
public class SocialuniAppConfigBO {
    //首页的tab栏目
    private String followTabName;
    private String homeTabName;
    private String cityTabName;
    private List<String> homeTabNames;

    //配置1，是否成年认证才可以发表自拍
    private String mustAdultCanPostPeoplePhoto;
    //配置2，是否填写校园才可以发帖
    private String mustHasSchoolCanPostTalk;
    //配置3，应用性别
    private String appGender;
}
