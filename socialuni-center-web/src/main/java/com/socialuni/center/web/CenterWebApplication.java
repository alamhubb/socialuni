package com.socialuni.center.web;

import com.socialuni.center.web.config.*;
import com.socialuni.social.web.sdk.config.EnableSocialWebSDK;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.socialuni.center")
@EnableJpaRepositories("com.socialuni.center")
@EnableFeignClients("com.socialuni.center")
@EntityScan("com.socialuni.center")
@EnableTransactionManagement
@EnableAsync
@EnableCaching
//复用feignBean不注册问题
@EnableSocialuni
@EnableSocialCloudServiceSDK
@MapperScan("com.socialuni.center")
@EnableSocialWebSDK
//复用feignBean不注册问题
@EnableConfigurationProperties({
        SocialuniAppProperties.class,
        SocialuniQQMapProperties.class,
        SocialuniCloudTencentCosProperties.class,
        SocialuniProviderQQProperties.class,
        SocialuniProviderWxProperties.class,
        SocialuniUserProperties.class
})
public class CenterWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterWebApplication.class, args);
    }
}
