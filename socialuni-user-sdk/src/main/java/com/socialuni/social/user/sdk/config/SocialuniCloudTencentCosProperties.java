package com.socialuni.social.user.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.cloud.tencent.cos")
public class SocialuniCloudTencentCosProperties {
    private String secretId;
    private String secretKey;
    private String bucket;
    private String region;
}
