package com.socialuni.social.common.api.model;

import com.socialuni.social.common.api.constant.GenderType;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class SocialuniAppConfigBO {
    //配置3，应用性别
    private String appGender = GenderType.all;
//    private String systemUserPhoneNum = "11111111111";
    //首页的tab栏目
    private String followTabName = "关注";
    private String homeTabName = "首页";
    private String cityTabName = "同城";
    private String selfSchoolTabName = "本校";
//    private String systemUserId = "关注";
    private List<String> tabNames = Arrays.asList("关注", "首页", "同城");
    private List<String> defaultChatGroups = Collections.singletonList("交友");

    //是否禁止未成年人内容
    private Boolean disableUnderageContent = false;

    //是否禁止内容中包含联系方式
    private Boolean disableContentHasContactInfo = false;

    //是否禁止内容中包含联系方式
    private Boolean mustSetPhoneCanPost = false;

    //是否禁止内容中包含二维码
    private Boolean disableContentHasQrCode = false;

    //是否必须填写校园才允许发帖
    private Boolean mustSetSchoolCanPost = false;

    //todo 相关功能未实现 用户必须实名认证
//    private Boolean mustIdentityAuthCanPost = false;

}
