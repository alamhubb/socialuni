package com.socialuni.social.web;

import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSocialSDK
public class SocialWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialWebApplication.class, args);
    }
}
