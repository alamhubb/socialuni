package com.socialuni.social.sdk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SocialuniTanceAppConfiguration {
   /* @Bean
    @ConditionalOnMissingBean(SocialuniAppConfig.class)
    public SocialuniAppConfig defaultSocialuniAppConfigurer() {
        return new SocialuniAppConfig();
    }*/

//    @Bean
//    @ConditionalOnMissingBean(SocialuniAppConfigInterface.class)
//    public SocialuniAppConfigInterface defaultSocialuniAppConfigInterface() {
//        SocialuniAppConfigInterface socialuniAppConfigInterface = new SocialuniAppConfigInterface() {};
//        return socialuniAppConfigInterface;
//    }
}
