package com.socialuni.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.user")
public class SocialuniUserProperties {

    private String userDefaultAvatar;
    private String tokenSecretKey;
    private String tokenName;
    private String loginUserKey;

}
