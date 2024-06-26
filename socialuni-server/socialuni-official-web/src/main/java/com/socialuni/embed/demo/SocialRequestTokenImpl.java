package com.socialuni.embed.demo;

import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import com.socialuni.embed.demo.service.TestUserService;
import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.sdk.config.SocialRequestUserConfigDefaultImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialRequestTokenImpl extends SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {
    @Resource
    TestUserService testUserService;

    @Override
    public String getToken() {
        String token = RequestUtil.getRequestValue("token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        TokenSocialuniTokenDO socialuniTokenDO = testUserService.getSocialuniToken(token);
        return socialuniTokenDO.getSocialuniToken();
    }
}
