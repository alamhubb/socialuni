package com.socialuni.social.common.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.cloud.tencent.sms")
public class SocialuniCloudTencentSmsProperties {
    private Integer appId;
    private String appKey;
    private Integer templateId;
    private String smsSign;
}
