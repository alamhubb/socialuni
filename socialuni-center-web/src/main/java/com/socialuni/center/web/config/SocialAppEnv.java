package com.socialuni.center.web.config;

import com.socialuni.center.web.constant.DevEnvType;
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

    //用来判断生产环境不支持同步，不能往生产环境同步内容
    public static Boolean getContainsProdEnv() {
        return activeEnv.contains(DevEnvType.prod);
    }

    //判断是否为开发生产环境，只有不为开发生产环境才能创建tag，开发和生产可以创建
    public static Boolean getIsDevProdEnv() {
        return activeEnv.contains(DevEnvType.devprod);
    }
}
