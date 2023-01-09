package com.socialuni.social.user.sdk;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.socialuni.social"})
@EnableSpringUtil
//@EnableCaching
public class SocialuniUserWebApplication {
    public static void main(String[] args) {

        SpringApplication.run(SocialuniUserWebApplication.class, args);
    }
}
