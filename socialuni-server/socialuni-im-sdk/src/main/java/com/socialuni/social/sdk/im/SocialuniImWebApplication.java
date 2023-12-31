package com.socialuni.social.sdk.im;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.socialuni.social"})
@EnableSpringUtil
public class SocialuniImWebApplication {
    public static void main(String[] args) {

        SpringApplication.run(SocialuniImWebApplication.class, args);
    }
}
