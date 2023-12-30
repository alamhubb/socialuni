package com.socialuni.social.common.sdk.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "socialuni.cloud.tencent.cos")
public class SocialuniCloudTencentCosProperties {
    private String secretId;
    private String secretKey;
    private String bucket;
    private String region;
}
