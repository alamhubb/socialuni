package com.socialuni.social.web.sdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SocialuniWebConfig {

    private static String tokenName;

    @Value("${socialuni.user.token-name:token}")
    public void setTokenName(String tokenName) {
        SocialuniWebConfig.tokenName = tokenName;
    }


    public static String getTokenName() {
        //为空则异常
        return tokenName;
    }
}