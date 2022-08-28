package com.socialuni.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni")
public class SocialuniProperties {

    private String secretKey;
    private String centralServerUrl;
    private String centralSocialuniId;
    private String socialuniId;
    //默认交友，可选校园
    private String socialType;

}
