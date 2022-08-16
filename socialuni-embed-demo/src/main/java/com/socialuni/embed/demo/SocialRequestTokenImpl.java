package com.socialuni.embed.demo;

import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import com.socialuni.embed.demo.service.TestUserService;
import com.socialuni.sdk.config.SocialRequestUserConfig;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class SocialRequestTokenImpl implements SocialRequestUserConfig {
    @Resource
    TestUserService testUserService;

    @Override
    public String getToken(HttpServletRequest request) {
        String token = RequestUtil.getRequestValue("token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        TokenSocialuniTokenDO socialuniTokenDO = testUserService.getSocialuniToken(token);
        return socialuniTokenDO.getSocialuniToken();
    }

    @Override
    public String getUserId(HttpServletRequest request) {
        return null;
    }
}