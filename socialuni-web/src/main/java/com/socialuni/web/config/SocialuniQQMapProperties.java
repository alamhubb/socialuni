package com.socialuni.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.map.qq")
public class SocialuniQQMapProperties {
    private String secretKey;
}
