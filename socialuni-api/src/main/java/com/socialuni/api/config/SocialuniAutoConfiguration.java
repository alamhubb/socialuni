package com.socialuni.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.socialuni.api")
@EnableConfigurationProperties({
        SocialuniProperties.class,
})
public class SocialuniAutoConfiguration {
}
