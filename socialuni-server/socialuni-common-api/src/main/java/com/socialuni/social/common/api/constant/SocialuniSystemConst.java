package com.socialuni.social.common.api.constant;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qinkaiyuan，
 * @date 2019-09-28 10:06
 */
@Component
public class SocialuniSystemConst {
    @Getter
    private static String userDefaultAvatar;
    private static String tagDefaultAvatar;
    @Getter
    private static String systemUserPhoneNum;
    //    private static  Long systemUserId = 1;
    @Getter
    public static final String testUserPhoneNum = "12222222222";
    private static String staticResourceUrl;
    private static String socialuniDevSecretKey;
    private static String socialuniServerUrl;
    private static String appSocialuniId;
    private static String centerSocialuniId;
    public static final Integer homeTalkQueryMinAge = -500;
    public static final Integer homeTalkQueryMaxAge = 500;

    @Value("${socialuni.system-user-phone-num:11111111111}")
    public void setSystemUserPhoneNum(String systemUserPhoneNum) {
        SocialuniSystemConst.systemUserPhoneNum = systemUserPhoneNum;
    }

    //放model合适只有创建时候才需要赋值这个
    @Value("${socialuni.user.user-default-avatar:https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png!avatar}")
    public void setUserDefaultAvatar(String userDefaultAvatar) {
        SocialuniSystemConst.userDefaultAvatar = userDefaultAvatar;
    }

    @Value("${socialuni.app.static-resource-url}")
    public void setStaticResourceUrl(String staticResourceUrl) {
        SocialuniSystemConst.staticResourceUrl = staticResourceUrl;
    }

//    @Value("${socialuni.app.system-user-id}")
//    public void setSystemUserId(Long systemUserId) {
//        SocialuniSystemConst.systemUserId = systemUserId;
//    }


    @Value("${socialuni.secret-key:#{null}}")
    public void setSocialuniDevSecretKey(String socialuniDevSecretKey) {
        SocialuniSystemConst.socialuniDevSecretKey = socialuniDevSecretKey;
    }

    @Value("${socialuni.central-server-url:https://api.socialuni.cn}")
    public void setSocialuniServerUrl(String socialuniServerUrl) {
        SocialuniSystemConst.socialuniServerUrl = socialuniServerUrl;
    }

    @Value("${socialuni.central-socialuni-id:a378e0b993414451a1c34b95d75398dd}")
    public void setCenterSocialuniId(String centerSocialuniId) {
        SocialuniSystemConst.centerSocialuniId = centerSocialuniId;
    }

    @Value("${socialuni.socialuni-id:#{null}}")
    public void setAppSocialuniId(String appSocialuniId) {
        SocialuniSystemConst.appSocialuniId = appSocialuniId;
    }


    public static String getTagDefaultAvatar() {
        return SocialuniSystemConst.getStaticResourceUrl() + "qingchi/static/qclogo.jpg!avatar";
    }

    /**
     * 获得系统用户id
     *
     * @return
     */

    public static String getStaticResourceUrl() {
        //为空则异常
        if (StringUtils.isEmpty(staticResourceUrl)) {
            throw new SocialParamsException("图片路径错误异常");
        }
        return staticResourceUrl + "/";
    }

    //是否配置了中心服务器
    public static boolean hasCenterServer() {
        //为空则异常
        return !serverIsCenter();
    }

    public static boolean serverIsCenter() {

        //前台不需要配置秘钥


        //然后我们得到的devId,就是1.
        //然后因为配置了秘钥，所以访问中心。
        //访问中心时，如果包含了秘钥，如果devId不为1， 则为中心。


        //当前服务器不为中心。



        //为空则异常
        return StringUtils.isEmpty(getDevSecretKey());
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

    private static String activeEnv;

    @Value("${spring.profiles.active}")
    public void setActiveEnv(String activeEnv) {
        SocialuniSystemConst.activeEnv = activeEnv;
    }

    //用来判断是否发送短信的
    //用来判断生产环境不支持同步，不能往生产环境同步内容
    public static Boolean getIsProdEnv() {
        return activeEnv.equals(DevEnvType.prod);
    }

    public static Boolean getIsLoaclPreEnv() {
        return activeEnv.equals(DevEnvType.localpre);
    }
}
