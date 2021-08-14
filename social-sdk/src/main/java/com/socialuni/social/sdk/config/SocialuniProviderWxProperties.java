package com.socialuni.social.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socialuni.provider.wx")
public class SocialuniProviderWxProperties {
    private String wxMpId;
    private String wxAppId;
    private String wxMpSecret;
    private String wxMerchantId;
    private String wxMerchantKey;
}
