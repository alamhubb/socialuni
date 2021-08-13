package com.socialuni.cloud.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        SocialuniCloudTencentSmsProperties.class,
})
public class SocialCloudServiceAutoConfiguration {
}
