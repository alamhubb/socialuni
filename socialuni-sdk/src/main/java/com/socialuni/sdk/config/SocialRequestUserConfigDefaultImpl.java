package com.socialuni.sdk.config;

import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.utils.SocialTokenDOUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebConfig;
import com.socialuni.social.web.sdk.utils.RequestUtil;

import javax.annotation.PostConstruct;


public class SocialRequestUserConfigDefaultImpl implements SocialRequestUserConfig {
    @PostConstruct
    public void sfasdf(){
        System.out.println("注册了默认的request");
    }

    @Override
    public String getToken(){
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