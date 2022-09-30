package com.socialuni.social.sdk.config;

import com.socialuni.social.tance.sdk.api.SocialRequestUserConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@Configurable
public class SocialRequestUserConfiguration {
    @Bean
    @ConditionalOnMissingBean(SocialRequestUserConfig.class)
    public SocialRequestUserConfig defaultSocialRequestUserConfig() {
        return new SocialRequestUserConfigDefaultImpl();
    }
}