package com.socialuni.social.sdk.utils;


import com.socialuni.social.entity.model.DO.RequestLogDO;

public class RequestLogUtil {
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

//    public static void remove() {
//        requestLogThread.remove();
//    }
}



