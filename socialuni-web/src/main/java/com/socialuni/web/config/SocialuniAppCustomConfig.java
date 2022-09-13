package com.socialuni.web.config;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniAppConfigBO;
import com.socialuni.sdk.constant.config.SocialuniAppType;
import com.socialuni.sdk.constant.socialuni.GenderType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class SocialuniAppCustomConfig extends SocialuniAppConfig {
    public static String appType = SocialuniAppType.school;

    //首页的tab栏目
    public static SocialuniAppConfigBO appConfig;

    //可以通过这里给appConfig自定义，可参考SocialuniAppType类的方式
    static {

    }
}
