package com.socialuni.social.sdk.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 当前线程的request工具类，从request中获取内容
 */
@Component
public class SocialHeaderUtil {
    //平台，android、mp
    public static final String platformKey = "platform";
    //渠道，qq，wx
    public static final String providerKey = "provider";

    public static String getPlatform() {
        String platform = SocialHeaderUtil.getProviderByReq();
        if (platform != null) {
            return platform;
        }
        platform = SocialuniRequestUtil.getHeader(SocialHeaderUtil.platformKey);
        if (platform != null) {
            HttpServletRequest request = SocialuniRequestUtil.getRequest();
            request.setAttribute(SocialHeaderUtil.platformKey, platform);
            return platform;
        }
        return null;
    }

    public static String getPlatformByReq() {
        //先从req中获取
        HttpServletRequest request = SocialuniRequestUtil.getRequest();
        return (String) request.getAttribute(SocialHeaderUtil.platformKey);
    }

    public static String getProvider() {
        String provider = SocialHeaderUtil.getProviderByReq();
        if (provider != null) {
            return provider;
        }
        provider = SocialuniRequestUtil.getHeader(SocialHeaderUtil.providerKey);
        if (provider != null) {
            HttpServletRequest request = SocialuniRequestUtil.getRequest();
            request.setAttribute(SocialHeaderUtil.providerKey, provider);
            return provider;
        }
        return null;
    }

    public static String getProviderByReq() {
        //先从req中获取
        HttpServletRequest request = SocialuniRequestUtil.getRequest();
        return (String) request.getAttribute(SocialHeaderUtil.providerKey);
    }
}
