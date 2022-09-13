package com.socialuni.sdk.config;

import com.socialuni.sdk.logic.domain.talk.SocialuniTalkQueryGenerateQueryBOByTabDomain;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@Configurable
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
