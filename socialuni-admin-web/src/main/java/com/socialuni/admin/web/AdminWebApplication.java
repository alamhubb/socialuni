package com.socialuni.admin.web;

import com.socialuni.api.config.EnableSocialuni;
import com.socialuni.center.sdk.config.EnableSocialuniCenterSDK;
import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableSocialSDK
@EnableSocialuniCenterSDK
@EnableJpaRepositories
@SpringBootApplication
@EnableCaching
@EntityScan("com.socialuni.admin")
public class AdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }

}
