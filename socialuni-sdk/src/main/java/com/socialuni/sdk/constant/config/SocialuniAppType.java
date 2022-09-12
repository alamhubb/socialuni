package com.socialuni.sdk.constant.config;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniAppConfigBO;

import java.util.Arrays;
import java.util.List;

public class SocialuniAppType {
    public static String school = "大学";
    public static String boy = "男孩";
    public static String girl = "女孩";
    public static String defaultType = "默认";

    public static List<String> genderTypeList = Arrays.asList(SocialuniAppType.boy, SocialuniAppType.girl);

    public static SocialuniAppConfigBO schoolAppConfigBO;
    public static SocialuniAppConfigBO genderAppConfigBO;
    public static SocialuniAppConfigBO defaultAppConfigBO;


    static {
        SocialuniAppConfigBO schoolAppConfigBO = new SocialuniAppConfigBO();
        schoolAppConfigBO.setFollowTabName("关注");
        schoolAppConfigBO.setHomeTabNames("首页");
        schoolAppConfigBO.setCityTabName("同城");

        //首页的tab栏目
        public String followTabName;
        public String homeTabName;
        public String cityTabName;
        public List<String> homeTabNames;

        //配置1，是否成年认证才可以发表自拍
        public String mustAdultCanPostPeoplePhoto;
        //配置2，是否填写校园才可以发帖
        public String mustHasSchoolCanPostTalk;
        //配置3，应用性别
        public String appGender;
    }
}
