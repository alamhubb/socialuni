package com.socialuni.social.user.sdk.utils;


import com.socialuni.social.user.sdk.model.DO.RequestLogDO;
import com.socialuni.social.user.sdk.logic.RequestLogStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class RequestLogUtil {
    private static RequestLogStore requestLogStore;

    @Resource
    public void setRequestLogStore(RequestLogStore requestLogStore) {
        RequestLogUtil.requestLogStore = requestLogStore;
    }

    public static RequestLogDO save(RequestLogDO operateLogDO) {
        return requestLogStore.save(operateLogDO);
//        return operateLogDO;
    }

    public static void saveAsync(RequestLogDO requestLogDO) {
        requestLogStore.saveAsync(requestLogDO);
    }

    public static void saveAsyncAndRemove(RequestLogDO requestLogDO) {
        requestLogStore.saveAsync(requestLogDO);
        RequestLogUtil.remove();
    }

    private static final ThreadLocal<RequestLogDO> requestLog = new ThreadLocal<>();

    private static final ThreadLocal<RequestAttributes> requestAttributes = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        RequestLogUtil.request.set(request);
    }

    public static HttpServletRequest getRequest() {
        return RequestLogUtil.request.get();
    }

    public static void setRequestAttributes(RequestAttributes requestAttributes) {
        RequestLogUtil.requestAttributes.set(requestAttributes);
    }

    public static RequestAttributes getRequestAttributes() {
        return requestAttributes.get();
    }

    public static void set(RequestLogDO requestLogDO) {
        requestLog.set(requestLogDO);
    }

    /*public static RequestLogDO getAndRemove() {
        RequestLogDO requestLogDO = requestLog.get();
        requestLog.remove();
        return requestLogDO;
    }*/

    public static RequestLogDO get() {
        return requestLog.get();
    }

    public static void remove() {
        requestLog.remove();
    }

}


