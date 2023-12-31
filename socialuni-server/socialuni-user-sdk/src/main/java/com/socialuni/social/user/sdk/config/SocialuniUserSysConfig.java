package com.socialuni.social.user.sdk.config;

import lombok.Data;

@Data
public class SocialuniUserSysConfig {
    //禁用后是否允许登录
    public static Boolean bandAllowLogin = true;
    public static String aesSecretKey = "4eb5088591d24a39ab2529fd4645c8c7";
}
