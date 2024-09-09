package com.socialuni.social.common.sdk.utils;


import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.constant.ErrorType;
import com.socialuni.social.common.api.dao.DO.ErrorRequestLogDO;
import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import com.socialuni.social.common.sdk.dao.store.RequestLogStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    //后端直接生成的
    public static void error(String errorMsg) {
        RequestLogDO errorRequestLogDO = RequestLogUtil.get();
        errorRequestLogDO = new RequestLogDO(errorRequestLogDO);
        errorRequestLogDO.setSuccess(false);
        errorRequestLogDO.setErrorCode(ErrorCode.BUSINESS_ERROR);
        errorRequestLogDO.setErrorType(ErrorType.error);
        errorRequestLogDO.setErrorMsg(errorMsg);
        errorRequestLogDO.setEndTime(new Date());
        requestLogStore.saveAsync(errorRequestLogDO);
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



