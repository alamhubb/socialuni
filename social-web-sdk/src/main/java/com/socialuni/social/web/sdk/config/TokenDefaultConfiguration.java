package com.socialuni.social.web.sdk.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@Configurable
public class TokenDefaultConfiguration {
    @PostConstruct
    public void sfasdf(){
        System.out.println("注册了默认的request");
    }

    @Bean
    @ConditionalOnMissingBean(SocialRequestToken.class)
    public SocialRequestToken defaultSocialRequestToken() {
        return new SocialRequestTokenImpl();
    }
}