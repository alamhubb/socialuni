package com.socialuni.social.user.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//必须有data否则不生效
@Data
@ConfigurationProperties(prefix = "socialuni.user")
public class SocialuniUserProperties {

    private String userDefaultAvatar;
    private String tokenSecretKey;
    private String passwordPublicKey;
    private String passwordPrivateKey;
    private String tokenName;
    private String loginUserKey;

}
