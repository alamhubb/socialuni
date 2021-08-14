package com.socialuni.social.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socialuni.provider.qq")
public class SocialuniProviderQQProperties {
    private String qqMpId;
    private String qqAppId;
    private String qqMpSecret;
    private String qqMerchantId;
    private String qqMerchantKey;
}
