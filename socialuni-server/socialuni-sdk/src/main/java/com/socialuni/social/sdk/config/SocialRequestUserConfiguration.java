package com.socialuni.social.sdk.config;

import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SocialRequestUserConfiguration {
    @Bean
    @ConditionalOnMissingBean(SocialRequestUserConfig.class)
    public SocialRequestUserConfig defaultSocialRequestUserConfig() {
        return new SocialRequestUserConfigDefaultImpl();
    }
}