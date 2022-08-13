package com.socialuni.sdk.config;

import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptorConfig;
import org.springframework.stereotype.Component;

@Component
public class SocialuniWebInterceptorConfigImpl implements SocialuniWebInterceptorConfig {
    @Override
    public String getUserId() {
        return SocialUserUtil.getMineUserIdStrInterceptor();
    }
}
