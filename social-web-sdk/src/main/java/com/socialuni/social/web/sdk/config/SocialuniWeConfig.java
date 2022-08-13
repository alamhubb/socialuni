package com.socialuni.social.web.sdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SocialuniWeConfig {

    private static String tokenName;

    @Value("${socialuni.user.token-name:token}")
    public void setTokenName(String tokenName) {
        SocialuniWeConfig.tokenName = tokenName;
    }


    public static String getTokenName() {
        System.out.println("tokenName:" + tokenName);
        //为空则异常
        return tokenName;
    }
}