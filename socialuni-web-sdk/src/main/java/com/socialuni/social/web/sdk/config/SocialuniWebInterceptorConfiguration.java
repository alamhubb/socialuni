package com.socialuni.social.web.sdk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialuniWebInterceptorConfiguration {

    @Bean
    @ConditionalOnMissingBean(SocialuniWebInterceptor.class)
    public SocialuniWebInterceptor defaultAuthInterceptor() {
        return new SocialuniWebInterceptor();
    }

}