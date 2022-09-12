package com.socialuni.sdk.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialuniAppConfig {
    private static String appType;
    //首页的tab栏目
    private static String followTabName;
    private static String homeTabName;
    private static String cityTabName;
    private static List<String> homeTabNames = new ArrayList<>();

    private static SocialuniAppConfigurer socialuniAppConfigurer;

    @Resource
    public void setSocialuniAppConfigurer(SocialuniAppConfigurer socialuniAppConfigurer) {
        SocialuniAppConfig.socialuniAppConfigurer = socialuniAppConfigurer;
    }

    @PostConstruct
    public static void initSocialuniAppConfigData() {
        SocialuniAppConfig.appType = socialuniAppConfigurer.getAppType();
        SocialuniAppConfig.followTabName = socialuniAppConfigurer.getFollowTabName();
        SocialuniAppConfig.homeTabName = socialuniAppConfigurer.getHomeTabName();
        SocialuniAppConfig.cityTabName = socialuniAppConfigurer.getCityTabName();
        SocialuniAppConfig.homeTabNames = socialuniAppConfigurer.getHomeTabNames();
    }

    public static String getAppType() {
        return appType;
    }

    public static String getFollowTabName() {
        return followTabName;
    }

    public static String getHomeTabName() {
        return homeTabName;
    }

    public static String getCityTabName() {
        return cityTabName;
    }

    public static List<String> getHomeTabNames() {
        return homeTabNames;
    }
}
