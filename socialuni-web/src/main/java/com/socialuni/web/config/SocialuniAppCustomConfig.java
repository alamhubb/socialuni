package com.socialuni.web.config;

import com.socialuni.sdk.constant.config.SocialuniAppType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniAppCustomConfig {
    public static String appType = SocialuniAppType.school;
}
