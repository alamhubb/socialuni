package com.socialuni.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni")
public class SocialuniProperties {
    //社交联盟数据服务地址
    private String serverUrl;
    private String secretKey;
}
