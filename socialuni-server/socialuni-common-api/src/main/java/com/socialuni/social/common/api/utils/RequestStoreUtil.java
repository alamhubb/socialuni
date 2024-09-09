package com.socialuni.social.common.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class RequestStoreUtil {
    private static final ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        RequestStoreUtil.request.set(request);
    }

    public static HttpServletRequest getRequest() {
        return RequestStoreUtil.request.get();
    }

}



