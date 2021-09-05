package com.socialuni.admin.web;

import com.socialuni.api.config.EnableSocialuni;
import com.socialuni.center.sdk.config.EnableSocialuniCenterSDK;
import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableSocialuni
@EnableSocialSDK
@EnableSocialuniCenterSDK
@EnableJpaRepositories
@SpringBootApplication
@EnableCaching
public class AdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }

}
