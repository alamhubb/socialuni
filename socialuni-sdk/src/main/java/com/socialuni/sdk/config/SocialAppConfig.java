package com.socialuni.sdk.config;

import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qinkaiyuan，
 * @date 2019-09-28 10:06
 */
@Component
public class SocialAppConfig {
    private static String userDefaultAvatar;
    private static Integer systemUserId;
    private static String staticResourceUrl;
    private static String socialuniDevSecretKey;
    private static String socialuniServerUrl;
    private static String appSocialuniId;
    private static String centerSocialuniId;
    public static final Integer homeTalkQueryMinAge = -500;
    public static final Integer homeTalkQueryMaxAge = 500;

    //放model合适只有创建时候才需要赋值这个
    @Value("${socialuni.user.user-default-avatar:https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png!avatar}")
    public void setUserDefaultAvatar(String userDefaultAvatar) {
        SocialAppConfig.userDefaultAvatar = userDefaultAvatar;
    }

    @Value("${socialuni.app.static-resource-url}")
    public void setStaticResourceUrl(String staticResourceUrl) {
        SocialAppConfig.staticResourceUrl = staticResourceUrl;
    }

    @Value("${socialuni.app.system-user-id}")
    public void setSystemUserId(Integer systemUserId) {
        SocialAppConfig.systemUserId = systemUserId;
    }


    @Value("${socialuni.secret-key:null}")
    public void setSocialuniDevSecretKey(String socialuniDevSecretKey) {
        SocialAppConfig.socialuniDevSecretKey = socialuniDevSecretKey;
    }

    @Value("${socialuni.server-url:#{null}}")
    public void setSocialuniServerUrl(String socialuniServerUrl) {
        SocialAppConfig.socialuniServerUrl = socialuniServerUrl;
    }

    @Value("${socialuni.center-socialuni-id:#{null}}")
    public void setCenterSocialuniId(String centerSocialuniId) {
        SocialAppConfig.centerSocialuniId = centerSocialuniId;
    }

    @Value("${socialuni.socialuni-id:#{null}}")
    public void setAppSocialuniId(String appSocialuniId) {
        SocialAppConfig.appSocialuniId = appSocialuniId;
    }

    public static String getUserDefaultAvatar() {
        return userDefaultAvatar;
    }

    public static Integer getSystemUserId() {
        return systemUserId;
    }

    public static String getStaticResourceUrl() {
        //为空则异常
        if (StringUtils.isEmpty(staticResourceUrl)) {
            throw new SocialParamsException("图片路径错误异常");
        }
        return staticResourceUrl + "/";
    }

    //是否配置了中心服务器
    public static boolean serverIsChild() {
        //为空则异常
        return !serverIsCenter();
    }

    public static boolean serverIsCenter() {
        //为空则异常
        return StringUtils.isEmpty(getSocialuniServerUrl());
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
