package com.socialuni.social.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.app")
public class SocialuniAppProperties {

    private String staticResourceUrl;
    private Integer systemUserId;

}
