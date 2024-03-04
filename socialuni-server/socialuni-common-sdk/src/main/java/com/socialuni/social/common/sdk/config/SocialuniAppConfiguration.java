package com.socialuni.social.common.sdk.config;

import com.socialuni.social.common.sdk.logic.domain.talk.SocialuniTalkQueryGenerateQueryBOByTabDomain;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialuniAppConfiguration {
   /* @Bean
    @ConditionalOnMissingBean(SocialuniAppConfig.class)
    public SocialuniAppConfig defaultSocialuniAppConfigurer() {
        return new SocialuniAppConfig();
    }*/

    @Bean
    @ConditionalOnMissingBean(SocialuniTalkQueryGenerateQueryBOByTabDomain.class)
    public SocialuniTalkQueryGenerateQueryBOByTabDomain defaultSocialuniTalkQueryGenerateQueryBOByTabDomain() {
        return new SocialuniTalkQueryGenerateQueryBOByTabDomain();
    }
}
