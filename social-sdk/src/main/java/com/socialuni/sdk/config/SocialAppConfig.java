package com.socialuni.sdk.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author qinkaiyuan，
 * @date 2019-09-28 10:06
 */
public class SocialAppConfig {
    public static String userDefaultAvatar;
    public static final Integer homeTalkQueryMinAge = -500;
    public static final Integer homeTalkQueryMaxAge = 500;

    //放model合适只有创建时候才需要赋值这个
    @Value("${social.user-default-avatar:https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/static/uploadimgmini.png!avatar}")
    public static void setUserDefaultAvatar(String userDefaultAvatar) {
        SocialAppConfig.userDefaultAvatar = userDefaultAvatar;
    }
}
