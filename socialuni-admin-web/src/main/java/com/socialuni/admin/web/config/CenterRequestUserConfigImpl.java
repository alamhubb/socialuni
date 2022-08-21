package com.socialuni.admin.web.config;


import com.socialuni.sdk.config.SocialRequestUserConfig;
import com.socialuni.sdk.config.SocialRequestUserConfigDefaultImpl;
import com.socialuni.sdk.config.SocialTokenUtil;
import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.repository.CommonTokenRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CenterRequestUserConfigImpl extends SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {
    @Override
    public String getToken() {
        String token = super.getToken();
        return token;
    }

    @Override
    public Integer getUserId() {
        //三方应用传的可以直接传用户id作为token
        return DevAccountUtils.getAdminDevAccountIdAllowNull();
    }
}
