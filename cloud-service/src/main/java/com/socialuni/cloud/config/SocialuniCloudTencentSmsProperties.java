package com.socialuni.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socialuni.cloud.tencent.sms")
public class SocialuniCloudTencentSmsProperties {
    private String appId;
    private String appKey;
    private String templateId;
    private String smsSign;
}
