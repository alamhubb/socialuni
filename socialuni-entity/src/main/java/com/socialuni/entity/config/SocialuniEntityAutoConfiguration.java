package com.socialuni.entity.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.socialuni.entity")
public class SocialuniEntityAutoConfiguration {
}
