package com.socialuni.social.sdk.config;

import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.utils.SocialTokenDOUtil;
import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.web.sdk.config.SocialuniWebConfig;
import com.socialuni.social.common.api.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;


public class SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {
    @Override
    public String getToken() {
        return RequestUtil.getRequestValue(SocialuniWebConfig.getTokenName());
    }

    @Override
    public Long getUserId() {
        //解析token
        SocialuniTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull();
        if (tokenDO == null) {
            return null;
        }
        return tokenDO.getUserId();
    }
}