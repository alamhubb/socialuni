package com.socialuni.social.sdk.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 当前线程的request工具类，从request中获取内容
 */
@Component
public class RequestUtils {
    private final Logger log = LoggerFactory.getLogger(getClass());
    //平台，android、mp
    public static final String platformKey = "platform";
    //渠道，qq，wx
    public static final String providerKey = "provider";

    public static String getPlatform() {
        String platform = RequestUtils.getProviderByReq();
        if (platform != null) {
            return platform;
        }
        platform = RequestUtils.getHeader(RequestUtils.platformKey);
        if (platform != null) {
            HttpServletRequest request = RequestUtils.getRequest();
            request.setAttribute(RequestUtils.platformKey, platform);
            return platform;
        }
        return null;
    }

    public static String getPlatformByReq() {
        //先从req中获取
        HttpServletRequest request = RequestUtils.getRequest();
        return (String) request.getAttribute(RequestUtils.platformKey);
    }

    public static String getProvider() {
        String provider = RequestUtils.getProviderByReq();
        if (provider != null) {
            return provider;
        }
        provider = RequestUtils.getHeader(RequestUtils.providerKey);
        if (provider != null) {
            HttpServletRequest request = RequestUtils.getRequest();
            request.setAttribute(RequestUtils.providerKey, provider);
            return provider;
        }
        return null;
    }

    public static String getProviderByReq() {
        //先从req中获取
        HttpServletRequest request = RequestUtils.getRequest();
        return (String) request.getAttribute(RequestUtils.providerKey);
    }


    public static String getHeader(String key) {
        String value = getRequest().getHeader(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    public static String getParameter(String key) {
        String value = getRequest().getParameter(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    //判断是否有效token
    public static boolean headerIsEmpty(String token) {
        return StringUtils.isEmpty(token)
                || "undefined".equals(token)
                || "null".equals(token)
                || "[object Null]".equals(token);
//                && !"\"[object Null]\"".equals(token)
    }

    //获取到当前线程绑定的请求对象
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    //暂未使用此方法
    public static Cookie[] getCookies() {
        return getRequest().getCookies();
    }

    //根据cookie名获取cookie值
    public static String getCookie(String cookieName) {
        Cookie[] cookies = getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
