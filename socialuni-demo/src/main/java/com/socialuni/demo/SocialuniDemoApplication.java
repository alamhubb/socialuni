package com.socialuni.demo;

import com.socialuni.api.config.EnableSocialuni;
import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableSocialSDK
@EnableSocialuni
//@EnableCaching
public class SocialuniDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialuniDemoApplication.class, args);
    }
}
