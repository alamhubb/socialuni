package com.qingchi.web;

import com.socialuni.api.config.EnableSocialuni;
import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableSocialSDK
@EnableSocialuni
@ComponentScan("com.qingchi")
@EnableJpaRepositories("com.qingchi")
@EntityScan("com.qingchi")
@EnableCaching
public class QingchiWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(QingchiWebApplication.class, args);
    }
}
