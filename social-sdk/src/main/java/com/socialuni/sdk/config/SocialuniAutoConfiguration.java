package com.socialuni.sdk.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.socialuni.sdk")
@EnableJpaRepositories("com.socialuni.sdk")
@EntityScan("com.socialuni.sdk")
@EnableConfigurationProperties({
        SocialuniUserProperties.class,
        SocialuniProviderQQProperties.class,
        SocialuniProviderWxProperties.class
})
public class SocialuniAutoConfiguration {
}
