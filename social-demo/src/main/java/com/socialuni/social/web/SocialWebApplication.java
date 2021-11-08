package com.socialuni.social.web;

import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableSocialSDK
//@EnableCaching
public class SocialWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialWebApplication.class, args);
    }
}
