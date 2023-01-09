package com.socialuni.social.sdk.config;

import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
import com.socialuni.social.user.sdk.utils.SocialTokenDOUtil;
import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.web.sdk.config.SocialuniWebConfig;
import com.socialuni.social.common.api.utils.RequestUtil;


public class SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {
    @Override
    public String getToken() {
        return RequestUtil.getHeader(SocialuniWebConfig.getTokenName());
    }

    @Override
    public Integer getUserId() {
        //解析token
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull();
        if (tokenDO == null) {
            return null;
        }
        return tokenDO.getUserId();
    }
}