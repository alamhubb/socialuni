package com.socialuni.social.sdk.config;

import com.socialuni.cloud.config.EnableSocialCloudServiceSDK;
import com.socialuni.social.entity.config.EnableSocialEntitySDK;
import com.socialuni.social.web.sdk.config.EnableSocialWebSDK;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableSocialCloudServiceSDK
@ComponentScan("com.socialuni.social.sdk")
@EnableJpaRepositories("com.socialuni.social.sdk")
@MapperScan("com.socialuni.social.sdk.mapper")
@EnableSocialEntitySDK
@EnableSocialWebSDK
@EnableTransactionManagement
@EnableAsync
@EnableConfigurationProperties({
        SocialuniAppProperties.class,
        SocialuniQQMapProperties.class,
        SocialuniCloudTencentCosProperties.class,
        SocialuniProviderQQProperties.class,
        SocialuniProviderWxProperties.class,
        SocialuniUserProperties.class
})
public class SocialSDKAutoConfiguration {
}
