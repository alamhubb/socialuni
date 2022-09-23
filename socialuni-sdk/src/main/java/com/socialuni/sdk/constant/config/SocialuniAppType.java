package com.socialuni.sdk.constant.config;

import com.socialuni.sdk.config.SocialuniAppConfigBO;
import com.socialuni.sdk.config.SocialuniAppMoreConfigBO;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SocialuniAppType {
    public static String school = "大学";
    public static String boy = "男孩";
    public static String girl = "女孩";
    public static String defaultType = "默认";

    public static List<String> genderTypeList = Arrays.asList(SocialuniAppType.boy, SocialuniAppType.girl);

    public static SocialuniAppConfigBO getAppConfigByAppType(String appType) {
        if (StringUtils.isEmpty(appType) || (appType.equals(SocialuniAppType.defaultType))) {
            return SocialuniAppType.getDefaultTypeAppConfig();
        } else if (appType.equals(SocialuniAppType.school)) {
            return SocialuniAppType.getSchoolTypeAppConfig();
        }
        throw new SocialParamsException("不支持的应用类型");
    }

    public static SocialuniAppConfigBO getDefaultTypeAppConfig() {
        SocialuniAppConfigBO appConfig = new SocialuniAppConfigBO();
        appConfig.setAppGender(GenderType.all);
        appConfig.setDisableUnderageContent(false);
        appConfig.setDisableContentHasQrCode(false);
        appConfig.setDisableContentHasContactInfo(false);
        appConfig.setMustSetSchoolCanPost(false);

        appConfig.setFollowTabName("关注");
        appConfig.setHomeTabName("首页");
        appConfig.setCityTabName("同城");
        appConfig.setTabNames(Arrays.asList("关注", "首页", "同城"));
        return appConfig;
    }

    public static SocialuniAppMoreConfigBO getDefaultAppMoreConfig() {
        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = new SocialuniAppMoreConfigBO();
        return socialuniAppMoreConfigBO;
    }

    public static SocialuniAppConfigBO getSchoolTypeAppConfig() {
        SocialuniAppConfigBO appConfig = new SocialuniAppConfigBO();

        appConfig.setFollowTabName("关注");
        appConfig.setHomeTabName("大学");
        appConfig.setCityTabName("同城");
        appConfig.setSelfSchoolTabName("本校");
        appConfig.setTabNames(Arrays.asList("关注", "大学", "同城", "本校"));
        appConfig.setAppGender(GenderType.all);
        appConfig.setDisableUnderageContent(false);
        appConfig.setDisableContentHasQrCode(false);
        appConfig.setDisableContentHasContactInfo(false);
        appConfig.setMustSetSchoolCanPost(true);

       /* SocialuniAppConfigBO boyAppConfigBO=new SocialuniAppConfigBO();
        boyAppConfigBO.setFollowTabName("关注");
        boyAppConfigBO.setHomeTabName("男生");
        boyAppConfigBO.setCityTabName("同城");
        boyAppConfigBO.setTabNames(Arrays.asList("关注","男生","同城"));
        boyAppConfigBO.setAppGender(GenderType.boy);
        SocialuniAppType.boyAppConfigBO=boyAppConfigBO;

        SocialuniAppConfigBO girlAppConfigBO=new SocialuniAppConfigBO();
        girlAppConfigBO.setFollowTabName("关注");
        girlAppConfigBO.setHomeTabName("女生");
        girlAppConfigBO.setCityTabName("同城");
        girlAppConfigBO.setTabNames(Arrays.asList("关注","女生","同城"));
        girlAppConfigBO.setAppGender(GenderType.girl);
        SocialuniAppType.girlAppConfigBO=girlAppConfigBO;*/
        return appConfig;
    }
}