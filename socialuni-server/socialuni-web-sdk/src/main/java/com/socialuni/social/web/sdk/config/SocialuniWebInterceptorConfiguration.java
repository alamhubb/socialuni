package com.socialuni.social.web.sdk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SocialuniWebInterceptorConfiguration {

    @Bean
    @Primary
    @ConditionalOnMissingBean(SocialuniWebInterceptor.class)
    public SocialuniWebInterceptor defaultAuthInterceptor() {
        return new SocialuniWebInterceptor();
    }

}