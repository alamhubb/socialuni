package com.socialuni.social;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.socialuni.social.sdk.config.EnableSocialuni;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@EnableSocialuni
@SpringBootApplication(scanBasePackages = {"com.socialuni.social"})
@EnableSpringUtil
//@EnableCaching
public class SocialuniWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialuniWebApplication.class, args);
    }
}
