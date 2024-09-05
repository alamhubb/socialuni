package com.socialuni.social.tance.dev.config;

import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SocialuniDevConfig {
    @Getter
    private static String systemUserPhoneNum;
    @Getter
    public static final String testUserPhoneNum = "12222222222";
    private static String socialuniDevSecretKey;
    private static String socialuniServerUrl;
    private static String appSocialuniId;
    private static String centerSocialuniId;

    @Value("${socialuni.system-user-phone-num:11111111111}")
    public void setSystemUserPhoneNum(String systemUserPhoneNum) {
        SocialuniDevConfig.systemUserPhoneNum = systemUserPhoneNum;
    }

    @Value("${socialuni.secret-key:#{null}}")
    public void setSocialuniDevSecretKey(String socialuniDevSecretKey) {
        SocialuniDevConfig.socialuniDevSecretKey = socialuniDevSecretKey;
    }

    @Value("${socialuni.central-server-url:https://api.socialuni.cn}")
    public void setSocialuniServerUrl(String socialuniServerUrl) {
        SocialuniDevConfig.socialuniServerUrl = socialuniServerUrl;
    }

    @Value("${socialuni.central-socialuni-id:a378e0b993414451a1c34b95d75398dd}")
    public void setCenterSocialuniId(String centerSocialuniId) {
        SocialuniDevConfig.centerSocialuniId = centerSocialuniId;
    }

    @Value("${socialuni.socialuni-id:#{null}}")
    public void setAppSocialuniId(String appSocialuniId) {
        SocialuniDevConfig.appSocialuniId = appSocialuniId;
    }

    //是否配置了中心服务器
    public static boolean hasCenterServer() {
        Integer devId = DevAccountFacade.getDevIdNullElseCenterDevId();

        Integer systemDevId = DevAccountFacade.getSystemDevAccountDevId();
        //秘钥不为空
        //且devid为系统id，才可以起调用中心，后面注释掉这个规则，其实就应该是判断秘钥是否为空
        return StringUtils.isNotBlank(getDevSecretKey());
    }

    public static boolean serverIsCenter() {
        return !SocialuniDevConfig.hasCenterServer();

    }

    //是否配置了中心服务器
    public static String getCenterSocialuniId() {
        if (StringUtils.isEmpty(centerSocialuniId) || centerSocialuniId.length() != 32) {
            return null;
        }
        //为空则异常
        return centerSocialuniId;
    }

    public static String getDevSecretKey() {
        if (StringUtils.isEmpty(socialuniDevSecretKey) || socialuniDevSecretKey.length() != 32) {
            return null;
        }
        //为空则异常
        return socialuniDevSecretKey;
    }

    public static String getSocialuniServerUrl() {
        if (StringUtils.isEmpty(socialuniServerUrl)) {
            return null;
        }
        //为空则异常
        return socialuniServerUrl;
    }

    //是否配置了中心服务器
    public static String getAppSocialuniId() {
        if (StringUtils.isEmpty(appSocialuniId) || appSocialuniId.length() != 32) {
            return null;
        }
        //为空则异常
        return appSocialuniId;
    }
}
