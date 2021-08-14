package com.socialuni.social.web.sdk.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.socialuni.social.web.sdk")
@EnableConfigurationProperties({
        SocialuniWebProperties.class,
})
public class SocialWebSDKAutoConfiguration {
}
