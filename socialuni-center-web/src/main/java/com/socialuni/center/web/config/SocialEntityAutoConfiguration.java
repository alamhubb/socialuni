package com.socialuni.center.web.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.socialuni.social.entity")
public class SocialEntityAutoConfiguration {
}
