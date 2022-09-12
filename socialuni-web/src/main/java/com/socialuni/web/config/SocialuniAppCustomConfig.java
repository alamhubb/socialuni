package com.socialuni.web.config;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.TalkTabType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SocialuniAppCustomConfig extends SocialuniAppConfig {
    //首页的tab栏目

    public static final String follow_name = "关注";
    public static final String home_name = "大学";
    public static final String city_name = "同城";
    public static final String self_school = "本校";

    public static final List<String> names = Arrays.asList(TalkTabType.follow_name, TalkTabType.home_name, TalkTabType.city_name);
}
