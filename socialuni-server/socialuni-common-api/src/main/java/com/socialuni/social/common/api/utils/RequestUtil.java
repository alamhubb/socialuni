package com.socialuni.social.common.api.utils;

import com.socialuni.social.common.api.constant.SocialWebHeaderName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 当前线程的request工具类，从request中获取内容
 */
@Slf4j
public class RequestUtil {


    //获取到当前线程绑定的请求对象
    public static HttpServletRequest getRequest() {
//        return RequestLogUtil.getRequest();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) return null;
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
        return servletRequestAttributes.getResponse();
    }

    public static String getParameter(String key) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        String value = request.getParameter(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    public static void setAttribute(String key, String value) {
        if (!headerIsEmpty(value)) {
            getRequest().setAttribute(key, value);
        }
    }

    public static String getAttribute(String key) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        String value = (String) request.getAttribute(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    public static String getHeader(String key) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        if (key == null) {
            return null;
        }
        String value = request.getHeader(key);
        if (headerIsEmpty(value)) {
            return null;
        }
        return value;
    }

    public static String getRequestValue(String key) {
        String value = getHeader(key);
        if (!headerIsEmpty(value)) {
            return value;
        }
        value = getParameter(key);
        if (!headerIsEmpty(value)) {
            return value;
        }
        value = getAttribute(key);
        if (!headerIsEmpty(value)) {
            return value;
        }
        return null;
    }

    public static String getSystem() {
        return RequestUtil.getHeader(SocialWebHeaderName.systemHeaderName);
    }

    public static String getProvider() {
        return RequestUtil.getHeader(SocialWebHeaderName.providerHeaderName);
    }

    public static String getUserLat() {
        return RequestUtil.getHeader(SocialWebHeaderName.city_lat);
    }


    public static String getUserLon() {
        return RequestUtil.getHeader(SocialWebHeaderName.city_lon);
    }

    public static String getUserAdCode() {
        return RequestUtil.getHeader(SocialWebHeaderName.city_adCode);
    }

    public static String getPlatform() {
        return RequestUtil.getRequestValue(SocialWebHeaderName.platformHeaderName);
    }

    /*public static String getDataOriginalSocialuniId() {
        return RequestUtil.getHeader(SocialFeignHeaderName.dataOriginalSocialuniId);
    }*/

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
