package com.socialuni.social.web.sdk.utils;


import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.store.RequestLogStore;

import javax.annotation.Resource;

public class RequestLogUtil {
    private static RequestLogStore requestLogStore;

    @Resource
    public void setOperateLogStore(RequestLogStore requestLogStore) {
        RequestLogUtil.requestLogStore = requestLogStore;
    }

    public static RequestLogDO save(RequestLogDO operateLogDO) {
        return requestLogStore.save(operateLogDO);
    }

    public static void saveAsync(RequestLogDO requestLogDO) {
        requestLogStore.saveAsync(requestLogDO);
    }

    public static void saveAsyncAndRemove(RequestLogDO requestLogDO) {
        requestLogStore.saveAsync(requestLogDO);
        RequestLogUtil.remove();
    }

    private static final ThreadLocal<RequestLogDO> requestLog = new ThreadLocal<>();

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



