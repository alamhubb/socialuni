package com.socialuni.sdk.config;

import com.socialuni.sdk.config.SocialRequestUserConfigDefaultImpl;
import com.socialuni.sdk.config.SocialuniAppConfigurer;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.logic.domain.talk.SocialuniTalkQueryGenerateQueryBOByTabDomain;
import com.socialuni.social.web.sdk.config.SocialRequestUserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

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
