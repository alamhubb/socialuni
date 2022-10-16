package com.socialuni.social.sdk.config;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.sdk.constant.config.SocialuniAppType;
import com.socialuni.social.sdk.utils.ObjectUtil;
import com.socialuni.social.tance.sdk.api.ConfigApi;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import org.springframework.stereotype.Component;
@Component
public class SocialuniAppConfig {

    @Deprecated
    static String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    @Deprecated
    static SocialuniAppConfigBO appConfig = SocialuniAppType.getDefaultTypeAppConfig();
    @Deprecated
    static SocialuniAppMoreConfigBO appMoreConfig = SocialuniAppType.getDefaultAppMoreConfig();

    @Deprecated
    public static void resetSocialuniAppConfig(String appType, SocialuniAppConfigBO appConfig, SocialuniAppMoreConfigBO appMoreConfig) {
        if (appType == null) {
            appType = SocialuniAppType.defaultType;
        }
        SocialuniAppConfigBO appTypeConfig = SocialuniAppType.getAppConfigByAppType(appType);
        //如果没设置自定义的使用默认的
        if (appConfig == null) {
            SocialuniAppConfig.appConfig = appTypeConfig;
        } else {
            //写了appConfig，则完全已自定义的为准
            SocialuniAppConfig.appConfig = ObjectUtil.mergeObjects(appConfig, appTypeConfig);
        }
        if (appMoreConfig != null) {
            SocialuniAppConfig.appMoreConfig = ObjectUtil.mergeObjects(appMoreConfig, SocialuniAppConfig.appMoreConfig);
        }
    }

    public static String getAppType() {
        return appType;
    }

    /**
     * 通过默认的开发者key获得配置
     * @return
     */
    public static SocialuniAppConfigBO getAppConfig() {
        ConfigApi configApi = SpringUtil.getBean(ConfigApi.class);
        String appSocialuniId = SocialuniSystemConst.getAppSocialuniId();
        SocialuniAppConfigBO appConfig = new SocialuniAppConfigBO();
        appConfig.setAppGender(configApi.getString(appSocialuniId,"appGender"));

        appConfig.setDisableUnderageContent(configApi.getBoolean(appSocialuniId,"disableUnderageContent"));
        appConfig.setDisableContentHasQrCode(configApi.getBoolean(appSocialuniId,"disableContentHasQrCode"));
        appConfig.setDisableContentHasContactInfo(configApi.getBoolean(appSocialuniId,"disableContentHasContactInfo"));
        appConfig.setMustSetSchoolCanPost(configApi.getBoolean(appSocialuniId,"mustSetSchoolCanPost"));

        appConfig.setFollowTabName(configApi.getString(appSocialuniId,"followTabName"));
        appConfig.setHomeTabName(configApi.getString(appSocialuniId,"homeTabName"));
        appConfig.setCityTabName(configApi.getString(appSocialuniId,"cityTabName"));
        //
        appConfig.setTabNames(configApi.getListString(appSocialuniId,"tabNames"));
        return appConfig;
    }

    /**
     *
     * @return
     */
    public static SocialuniAppMoreConfigBO getAppMoreConfig() {
        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = new SocialuniAppMoreConfigBO();
        return socialuniAppMoreConfigBO;
    }
}
