package com.socialuni.social.web.sdk.config;


import com.socialuni.social.common.repository.DelegateExtendBaseRepository;
import com.socialuni.social.tance.repository.PublishDataTanceBaseRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.socialuni.social.web.sdk")
//@EnableJpaRepositories("com.socialuni.social.web.sdk")
@EnableJpaRepositories(value = "com.socialuni.social.web.sdk",repositoryBaseClass  = DelegateExtendBaseRepository.class)
@EntityScan("com.socialuni.social.web.sdk")
@EnableConfigurationProperties({
        SocialuniWebProperties.class
})
public class SocialWebSDKAutoConfiguration {
}
