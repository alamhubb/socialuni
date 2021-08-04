package com.socialuni.demo;

import com.socialuni.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableSocialSDK
public class SocialDemoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialDemoWebApplication.class, args);
    }
}
