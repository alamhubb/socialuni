package com.socialuni.social.common.sdk.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.map.qq")
public class SocialuniQQMapProperties {
    private String secretKey;
}
