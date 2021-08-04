package com.socialuni.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni")
public class SocialuniProperties {

    private String secretKey;
    private String serverUrl;

}
