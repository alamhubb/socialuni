package com.socialuni.center.web;

import com.socialuni.center.sdk.config.EnableSocialuniCenterSDK;
import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableSocialuniCenterSDK
@EnableSocialSDK
@EnableAsync
//@EnableCaching
public class CenterWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterWebApplication.class, args);
    }
}
