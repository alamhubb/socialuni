package com.socialuni.web;

import com.socialuni.web.config.EnableSocialuni;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableSocialuni
@SpringBootApplication
@EnableCaching
public class SocialuniWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialuniWebApplication.class, args);
    }
}
