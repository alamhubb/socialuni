package com.socialuni.center.sdk.config;

import com.socialuni.social.sdk.config.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.socialuni.center.sdk")
@EnableJpaRepositories("com.socialuni.center.sdk")
@EnableFeignClients("com.socialuni.center.sdk")
@EnableConfigurationProperties({
        SocialuniAdminProperties.class,
})
public class SocialuniCenterAutoConfiguration {
}
