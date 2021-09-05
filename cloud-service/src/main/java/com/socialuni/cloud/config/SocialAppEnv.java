package com.socialuni.cloud.config;

import com.socialuni.cloud.constant.DevEnvType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qinkaiyuan，
 * @date 2019-09-28 10:06
 */
@Component
public class SocialAppEnv {
    private static String activeEnv;

    @Value("${spring.profiles.active}")
    public void setActiveEnv(String activeEnv) {
        SocialAppEnv.activeEnv = activeEnv;
    }

    //用来判断是否发送短信的
    //用来判断生产环境不支持同步，不能往生产环境同步内容
    public static Boolean getIsProdEnv() {
        return activeEnv.equals(DevEnvType.prod);
    }

    //判断是否为demo环境，只有不为demo环境才能创建tag，开发和生产可以创建
    public static Boolean getIsDemoEnv() {
        return activeEnv.equals(DevEnvType.demo);
    }
}
