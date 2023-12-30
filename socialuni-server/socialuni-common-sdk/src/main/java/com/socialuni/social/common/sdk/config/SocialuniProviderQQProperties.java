package com.socialuni.social.common.sdk.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "socialuni.provider.qq")
public class SocialuniProviderQQProperties {
    private String qqMpId;
    private String qqAppId;
    private String qqMpSecret;
    private String qqMerchantId;
    private String qqMerchantKey;
}
