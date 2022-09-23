package com.socialuni.social;

import com.socialuni.social.sdk.config.EnableSocialuni;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSocialuni
@SpringBootApplication(scanBasePackages = {"com.socialuni.social"})
//@EnableCaching
public class SocialuniWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialuniWebApplication.class, args);
    }
}
