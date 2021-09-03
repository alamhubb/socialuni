package com.socialuni.center.sdk.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.socialuni.center.sdk")
@EnableJpaRepositories("com.socialuni.center.sdk")
public class SocialuniCenterAutoConfiguration {
}
