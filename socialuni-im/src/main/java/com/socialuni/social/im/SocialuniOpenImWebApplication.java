package com.socialuni.social.im;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.socialuni.social"})
@EnableSpringUtil
@EnableFeignClients("com.socialuni.social")
@EnableAsync
//@EnableCaching
public class SocialuniOpenImWebApplication {
    public static void main(String[] args) {

        SpringApplication.run(SocialuniOpenImWebApplication.class, args);
    }
}
