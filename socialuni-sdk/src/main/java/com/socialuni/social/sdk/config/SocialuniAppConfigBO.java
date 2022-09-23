package com.socialuni.social.sdk.config;

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

    //是否禁止未成年人内容
    private Boolean disableUnderageContent;

    //是否禁止内容中包含联系方式
    private Boolean disableContentHasContactInfo;

    //是否禁止内容中包含二维码
    private Boolean disableContentHasQrCode;

    //是否必须填写校园才允许发帖
    private Boolean mustSetSchoolCanPost;

    //todo 相关功能未实现 用户必须实名认证
    private Boolean mustIdentityAuthCanPost;

    //配置3，应用性别
    private String appGender;
}
