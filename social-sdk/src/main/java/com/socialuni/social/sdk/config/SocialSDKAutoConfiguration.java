package com.socialuni.social.sdk.config;

import com.socialuni.cloud.config.EnableSocialCloudServiceSDK;
import com.socialuni.social.entity.config.EnableSocialEntitySDK;
import com.socialuni.social.web.sdk.config.EnableSocialWebSDK;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableSocialCloudServiceSDK
@ComponentScan("com.socialuni.social.sdk")
@EnableJpaRepositories("com.socialuni.social.sdk")
@EnableSocialEntitySDK
@EnableSocialWebSDK
@EnableConfigurationProperties({
        SocialuniAliMapProperties.class,
        SocialuniCloudTencentCosProperties.class,
        SocialuniProviderQQProperties.class,
        SocialuniProviderWxProperties.class,
        SocialuniUserProperties.class
})
public class SocialSDKAutoConfiguration {
}
