package com.socialuni.center.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.admin")
public class SocialuniAdminProperties {
    private String devServerUrl;
}
