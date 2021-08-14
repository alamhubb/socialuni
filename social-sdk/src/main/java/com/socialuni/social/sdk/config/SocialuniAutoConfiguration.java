package com.socialuni.social.sdk.config;

import com.socialuni.cloud.config.EnableSocialCloudServiceSDK;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableSocialCloudServiceSDK
@ComponentScan("com.socialuni.social.sdk")
@EnableJpaRepositories("com.socialuni.social.sdk")
@EntityScan("com.socialuni.social.entity")
@EnableConfigurationProperties({
        SocialuniAliMapProperties.class,
        SocialuniCloudTencentCosProperties.class,
        SocialuniProviderQQProperties.class,
        SocialuniProviderWxProperties.class,
        SocialuniUserProperties.class
})
public class SocialuniAutoConfiguration {
}
