package com.socialuni.center.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.provider.qq")
public class SocialuniProviderQQProperties {
    private String qqMpId;
    private String qqAppId;
    private String qqMpSecret;
    private String qqMerchantId;
    private String qqMerchantKey;
}
