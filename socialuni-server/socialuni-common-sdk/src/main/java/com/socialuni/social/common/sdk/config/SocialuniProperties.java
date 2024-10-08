package com.socialuni.social.common.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni")
public class SocialuniProperties {

    private String secretKey;
    private String centralServerUrl;
    private String openImServerUrl;
    private String openImSecret;
    private String centralSocialuniId;
    //什么用，注册的时候用写死用
    private String socialuniId;
    private String systemUserPhoneNum;
    //默认交友，可选校园
    private String socialType;

}
