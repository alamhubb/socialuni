package com.socialuni.center.sdk.config;

import com.socialuni.api.config.EnableSocialuni;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.socialuni.center")
@EnableJpaRepositories("com.socialuni.center")
@EnableFeignClients("com.socialuni.center")
@EntityScan("com.socialuni.center")
//复用feignBean不注册问题
@EnableSocialuni
public class SocialuniCenterAutoConfiguration {
}
