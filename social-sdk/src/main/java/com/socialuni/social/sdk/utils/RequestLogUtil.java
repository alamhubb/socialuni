package com.socialuni.social.sdk.utils;


import com.socialuni.social.entity.model.DO.OperateLogDO;

public class RequestLogUtil {
    private static final ThreadLocal<OperateLogDO> requestLog = new ThreadLocal<>();

    public static void set(OperateLogDO operateLogDO) {
        requestLog.set(operateLogDO);
    }

    public static OperateLogDO getAndRemove() {
        OperateLogDO operateLogDO = requestLog.get();
        requestLog.remove();
        return operateLogDO;
    }

    public static OperateLogDO get() {
        return requestLog.get();
    }

//    public static void remove() {
//        requestLogThread.remove();
//    }
}



