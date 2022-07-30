package com.socialuni.social.web.sdk.utils;

import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.web.sdk.constant.SocialWebHeaderName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 当前线程的request工具类，从request中获取内容
 */
public class RequestUtil {


    //获取到当前线程绑定的请求对象
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    public static String getParameter(String key) {
        String value = getRequest().getParameter(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    public static String getHeader(String key) {
        String value = getRequest().getHeader(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    public static String getSystem() {
        return RequestUtil.getHeader(SocialWebHeaderName.systemHeaderName);
    }

    public static String getProvider() {
        return RequestUtil.getHeader(SocialWebHeaderName.providerHeaderName);
    }

    public static String getPlatform() {
        return RequestUtil.getHeader(SocialWebHeaderName.platformHeaderName);
    }

    public static String getDataSocialuniId() {
        return RequestUtil.getHeader(SocialFeignHeaderName.dataSocialuniId);
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

    //暂未使用此方法
    public static Cookie[] getCookies() {
        return getRequest().getCookies();
    }

    //判断是否有效token
    public static boolean headerIsEmpty(String headerName) {
        return StringUtils.isEmpty(headerName)
                || "undefined".equals(headerName)
                || "null".equals(headerName)
                || "[object Undefined]".equals(headerName)
                || "[object Null]".equals(headerName);
//                && !"\"[object Null]\"".equals(token)
    }
}
