package com.socialuni.cloud.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        SocialuniCloudTencentSmsProperties.class,
})
@ComponentScan("com.socialuni.cloud")
public class SocialCloudServiceAutoConfiguration {
}
