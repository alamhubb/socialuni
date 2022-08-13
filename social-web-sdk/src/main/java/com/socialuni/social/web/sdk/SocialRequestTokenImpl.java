package com.socialuni.social.web.sdk;

import com.socialuni.social.web.sdk.config.SocialuniWeConfig;
import com.socialuni.social.web.sdk.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;


public class SocialRequestTokenImpl implements SocialRequestToken{

    @Override
    public String getToken(HttpServletRequest request){
        return RequestUtil.getHeader(SocialuniWeConfig.getTokenName());
    }
}