package com.socialuni.admin.web.config;


import com.socialuni.social.user.sdk.api.SocialRequestUserConfig;
import com.socialuni.social.sdk.config.SocialRequestUserConfigDefaultImpl;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
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
