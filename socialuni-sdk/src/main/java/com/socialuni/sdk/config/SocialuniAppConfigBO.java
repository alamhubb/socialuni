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
    

    //配置1，是否禁止未成年人内容
    private Boolean disableUnderageContent = false;
    //配置2，是否必须填写校园才允许发帖
    private Boolean mustSetSchoolCanPost = false;
    //配置3，应用性别
    private String appGender = GenderType.all;
}
