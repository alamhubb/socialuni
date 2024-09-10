package com.socialuni.social.common.api.utils;

import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.constant.SocialWebHeaderName;
import com.socialuni.social.common.api.constant.SocialuniSupportProviderType;
import com.socialuni.social.common.api.constant.SystemType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


/**
 * 当前线程的request工具类，从request中获取内容
 */
@Slf4j
public class RequestUtil {

    //获取到当前线程绑定的请求对象
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
            request = servletRequestAttributes.getRequest();
            if (!ObjectUtils.isEmpty(request)) {
                return request;
            }
        }
        request = RequestStoreUtil.getRequest();
        return request;
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
        return servletRequestAttributes.getResponse();
    }

    public static String getParameter(String key) {
        HttpServletRequest request = getRequest();
       /* HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        InputStream inputStream;
        //省略一些代码
        try {
            inputStream = inputMessage.getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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
        String value = getParameter(key);
        if (!headerIsEmpty(value)) {
            return value;
        }
        value = getHeader(key);
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
        String system = RequestUtil.getRequestValue(SocialWebHeaderName.systemHeaderName);
        if (StringUtils.isEmpty(system)) {
            return null;
        }
        if (SystemType.values.contains(system)) {
            return system;
        }
        throw new SocialParamsException(SystemType.notSupportTypeErrorMsg);
    }

    public static String getProvider() {
        String provider = RequestUtil.getRequestValue(SocialWebHeaderName.providerHeaderName);
        if (StringUtils.isEmpty(provider)) {
            return null;
        }
        return SocialuniSupportProviderType.checkSupportType(provider);
    }

    public static String getPlatform() {
        String platform = RequestUtil.getRequestValue(SocialWebHeaderName.platformHeaderName);
        if (StringUtils.isEmpty(platform)) {
            return null;
        }
        if (PlatformType.values.contains(platform)) {
            return platform;
        }
        throw new SocialParamsException(PlatformType.notSupportTypeErrorMsg);
    }

    public static String getDeviceUid() {
        String device = RequestUtil.getRequestValue(SocialWebHeaderName.deviceUidKey);
        return device;
    }


    public static String getUserAgent() {
        String device = RequestUtil.getRequestValue("User-Agent");
        return device;
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
