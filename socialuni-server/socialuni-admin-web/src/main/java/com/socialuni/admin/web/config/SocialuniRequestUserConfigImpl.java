package com.socialuni.admin.web.config;


import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.config.SocialRequestUserConfigDefaultImpl;
import org.springframework.stereotype.Component;

@Component
public class SocialuniRequestUserConfigImpl extends SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {
    @Override
    public String getToken() {
        String token = super.getToken();
        return token;
    }

    @Override
    public Integer getUserId() {
        //三方应用传的可以直接传用户id作为token
        return DevAccountFacade.getAdminDevAccountIdAllowNull();
    }
}