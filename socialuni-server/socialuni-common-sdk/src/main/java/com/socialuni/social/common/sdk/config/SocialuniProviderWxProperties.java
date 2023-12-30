package com.socialuni.social.common.sdk.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "socialuni.provider.wx")
public class SocialuniProviderWxProperties {
    private String wxMpId;
    private String wxAppId;
    private String wxMpSecret;
    private String wxMerchantId;
    private String wxMerchantKey;
}
