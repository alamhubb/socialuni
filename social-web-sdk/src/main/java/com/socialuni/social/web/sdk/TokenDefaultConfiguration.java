package com.socialuni.social.web.sdk;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@Configurable
public class TokenDefaultConfiguration {
    @Bean
    @ConditionalOnMissingBean(SocialRequestToken.class)
    public SocialRequestToken defaultSocialRequestToken() {
        return new SocialRequestTokenImpl();
    }
}