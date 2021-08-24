package com.socialuni.social.web.sdk.utils;

import com.socialuni.social.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 当前线程的request工具类，从request中获取内容
 */
public class RequestIdUtil {
    static int i = 0;

    public static synchronized int addId() {
        ++i;
        return i;
    }
}
