package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.config.SocialuniAppType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SocialuniAppConfig {
    public static String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    public static SocialuniAppConfigBO appConfig;
}
