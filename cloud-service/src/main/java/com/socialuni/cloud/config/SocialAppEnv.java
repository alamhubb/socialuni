package com.socialuni.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qinkaiyuan，
 * @date 2019-09-28 10:06
 */
@Component
public class SocialAppEnv {
    public static final String prodEnv = "prod";
    private static String activeEnv;

    @Value("${spring.profiles.active}")
    public void setActiveEnv(String activeEnv) {
        SocialAppEnv.activeEnv = activeEnv;
    }

    public static Boolean getIsProdEnv() {
        return activeEnv.equals(SocialAppEnv.prodEnv);
    }
}
