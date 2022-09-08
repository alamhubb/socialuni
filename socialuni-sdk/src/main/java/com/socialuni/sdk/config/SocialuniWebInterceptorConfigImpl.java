package com.socialuni.sdk.config;

import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptorConfig;
import org.springframework.stereotype.Component;

@Component
public class SocialuniWebInterceptorConfigImpl implements SocialuniWebInterceptorConfig {
    @Override
    public String getUserId() {
        return SocialuniUserUtil.getMineUserIdStrInterceptor();
    }
}
