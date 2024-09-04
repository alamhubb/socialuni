package com.socialuni.social.common.api.config;

public interface SocialRequestUserConfig {
    //支持用户重写token，有那种根据token，获取其他token返回的情况
    String getToken();

    Long getUserId();
}