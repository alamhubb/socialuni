package com.socialuni.social.demo;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.socialuni.social.sdk.config.EnableSocialuni;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSocialuni
@SpringBootApplication(scanBasePackages = {"com.socialuni.social"})
@EnableSpringUtil
//@EnableCaching
public class SocialuniExampleSingleWebApplication {
    public static void main(String[] args) {

        SpringApplication.run(SocialuniExampleSingleWebApplication.class, args);
    }
}
