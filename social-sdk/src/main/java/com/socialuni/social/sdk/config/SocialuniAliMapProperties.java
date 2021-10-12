package com.socialuni.social.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.map.ali")
public class SocialuniAliMapProperties {
    private String secretKey;
}
