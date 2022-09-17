package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.config.SocialuniAppType;
import com.socialuni.sdk.utils.ObjectUtil;

public class SocialuniAppConfig {
    static String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    static SocialuniAppConfigBO appConfig = SocialuniAppType.getDefaultTypeAppConfig();
    static SocialuniAppMoreConfigBO appMoreConfig = SocialuniAppType.getDefaultAppMoreConfig();

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

    public static SocialuniAppConfigBO getAppConfig() {
        return appConfig;
    }

    public static SocialuniAppMoreConfigBO getAppMoreConfig() {
        return appMoreConfig;
    }
}
