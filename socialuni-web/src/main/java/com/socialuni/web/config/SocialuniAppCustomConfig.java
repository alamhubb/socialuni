package com.socialuni.web.config;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.config.SocialuniAppType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniAppCustomConfig extends SocialuniAppConfig {
    public static String appType = SocialuniAppType.school;
}
