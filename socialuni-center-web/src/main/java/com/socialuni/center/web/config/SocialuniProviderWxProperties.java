package com.socialuni.center.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.provider.wx")
public class SocialuniProviderWxProperties {
    private String wxMpId;
    private String wxAppId;
    private String wxMpSecret;
    private String wxMerchantId;
    private String wxMerchantKey;
}
