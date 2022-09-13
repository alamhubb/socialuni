package com.socialuni.sdk.constant.config;

import com.socialuni.sdk.config.SocialuniAppConfigBO;
import com.socialuni.sdk.constant.socialuni.GenderType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialuniAppType {
    public static String school = "大学";
    public static String boy = "男孩";
    public static String girl = "女孩";
    public static String defaultType = "默认";

    public static List<String> genderTypeList = Arrays.asList(SocialuniAppType.boy, SocialuniAppType.girl);

    public static SocialuniAppConfigBO defaultAppConfigBO;
    public static SocialuniAppConfigBO boyAppConfigBO;
    public static SocialuniAppConfigBO girlAppConfigBO;
    public static SocialuniAppConfigBO schoolAppConfigBO;

    public static Map<String,SocialuniAppConfigBO> appTypeMap;


    static {
        SocialuniAppConfigBO defaultAppConfigBO = new SocialuniAppConfigBO();
        defaultAppConfigBO.setFollowTabName("关注");
        defaultAppConfigBO.setHomeTabName("首页");
        defaultAppConfigBO.setCityTabName("同城");
        defaultAppConfigBO.setTabNames(Arrays.asList("关注", "首页", "同城"));
        defaultAppConfigBO.setNeedAdultCanPostPeoplePhoto(true);
        SocialuniAppType.defaultAppConfigBO = defaultAppConfigBO;


        SocialuniAppConfigBO boyAppConfigBO = new SocialuniAppConfigBO();
        boyAppConfigBO.setFollowTabName("关注");
        boyAppConfigBO.setHomeTabName("男生");
        boyAppConfigBO.setCityTabName("同城");
        boyAppConfigBO.setTabNames(Arrays.asList("关注", "男生", "同城"));
        boyAppConfigBO.setAppGender(GenderType.boy);
        SocialuniAppType.boyAppConfigBO = boyAppConfigBO;

        SocialuniAppConfigBO girlAppConfigBO = new SocialuniAppConfigBO();
        girlAppConfigBO.setFollowTabName("关注");
        girlAppConfigBO.setHomeTabName("女生");
        girlAppConfigBO.setCityTabName("同城");
        girlAppConfigBO.setTabNames(Arrays.asList("关注", "女生", "同城"));
        girlAppConfigBO.setAppGender(GenderType.girl);
        SocialuniAppType.girlAppConfigBO = girlAppConfigBO;


        SocialuniAppConfigBO schoolAppConfigBO = new SocialuniAppConfigBO();
        schoolAppConfigBO.setFollowTabName("关注");
        schoolAppConfigBO.setHomeTabName("大学");
        schoolAppConfigBO.setCityTabName("同城");
        schoolAppConfigBO.setSelfSchoolTabName("本校");
        schoolAppConfigBO.setTabNames(Arrays.asList("关注", "大学", "同城", "本校"));
        schoolAppConfigBO.setHasSchoolCanPostTalk(true);
        SocialuniAppType.schoolAppConfigBO = schoolAppConfigBO;

        appTypeMap = new HashMap<>();
        appTypeMap.put(SocialuniAppType.school,SocialuniAppType.schoolAppConfigBO);
        appTypeMap.put(SocialuniAppType.boy,SocialuniAppType.boyAppConfigBO);
        appTypeMap.put(SocialuniAppType.girl,SocialuniAppType.girlAppConfigBO);
        appTypeMap.put(SocialuniAppType.defaultType,SocialuniAppType.defaultAppConfigBO);
    }
}
