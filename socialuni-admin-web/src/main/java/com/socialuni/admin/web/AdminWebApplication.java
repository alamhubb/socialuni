package com.socialuni.admin.web;

import com.socialuni.api.config.EnableSocialuni;
import com.socialuni.center.sdk.config.EnableSocialuniCenterSDK;
import com.socialuni.cloud.config.EnableSocialCloudServiceSDK;
import com.socialuni.entity.config.EnableSocialuniEntitySDK;
import com.socialuni.social.entity.config.EnableSocialEntitySDK;
import com.socialuni.social.web.sdk.config.EnableSocialWebSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableSocialuni
@EnableSocialCloudServiceSDK
@EnableTransactionManagement
@EnableSocialEntitySDK
@EnableSocialuniEntitySDK
@EnableSocialuniCenterSDK
@EnableSocialWebSDK
@EntityScan("com.socialuni.admin")
@EnableJpaRepositories
@SpringBootApplication
public class AdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }

}
