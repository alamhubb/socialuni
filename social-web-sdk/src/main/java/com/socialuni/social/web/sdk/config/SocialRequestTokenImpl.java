package com.socialuni.social.web.sdk.config;

import com.socialuni.social.web.sdk.utils.RequestUtil;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;


public class SocialRequestTokenImpl implements SocialRequestToken{
    @PostConstruct
    public void sfasdf(){
        System.out.println("注册了默认的request");
    }

    @Override
    public String getToken(HttpServletRequest request){
        return RequestUtil.getHeader(SocialuniWeConfig.getTokenName());
    }
}