package com.socialuni.embed.demo;

import com.socialuni.social.common.sdk.config.EnableSocialuni;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableSocialuni
@EnableJpaRepositories("com.socialuni.embed")
@EntityScan("com.socialuni.embed")
public class SocialuniEmbedDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialuniEmbedDemoApplication.class, args);
    }

}
