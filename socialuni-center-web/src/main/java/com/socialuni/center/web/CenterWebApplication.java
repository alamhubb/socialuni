package com.socialuni.center.web;

import com.socialuni.entity.config.EnableSocialuniEntitySDK;
import com.socialuni.social.sdk.config.EnableSocialSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableCaching
@EnableJpaRepositories({"com.socialuni.center"})
@ComponentScan({"com.socialuni.center"})
@EnableSocialuniEntitySDK
@EnableSocialSDK
@EnableAsync
public class CenterWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterWebApplication.class, args);
    }
}
