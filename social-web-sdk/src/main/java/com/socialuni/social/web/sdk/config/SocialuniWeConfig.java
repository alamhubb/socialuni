package com.socialuni.social.web.sdk.config;

import org.springframework.beans.factory.annotation.Value;

public class SocialuniWeConfig{

    private static String tokenName;

    @Value("${socialuni.user.token-name:token}")
    public void setTokenName(String tokenName) {
        SocialuniWeConfig.tokenName = tokenName;
    }


    public static String getTokenName() {
        //为空则异常
        return tokenName;
    }
}