package com.socialuni.center.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.socialuni.api")
@ComponentScan("com.socialuni.api")
@EnableConfigurationProperties({
        SocialuniProperties.class,
})
public class SocialuniAutoConfiguration {
}
