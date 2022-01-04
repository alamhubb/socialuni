package com.socialuni.social.web.sdk.utils;


import com.socialuni.social.web.sdk.model.ErrorRequestLogDO;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.store.ErrorRequestLogStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ErrorRequestLogUtil {
    private static ErrorRequestLogStore errorRequestLogStore;

    @Resource
    public void setErrorRequestLogStore(ErrorRequestLogStore errorRequestLogStore) {
        ErrorRequestLogUtil.errorRequestLogStore = errorRequestLogStore;
    }

    public static void saveAsync(RequestLogDO requestLogDO) {
        ErrorRequestLogDO errorRequestLogDO = new ErrorRequestLogDO(requestLogDO);
        errorRequestLogStore.saveAsync(errorRequestLogDO);
    }

    public static void saveAsync(String errorMsg) {
        RequestLogDO requestLogDO = RequestLogUtil.get();
        ErrorRequestLogDO errorRequestLogDO = new ErrorRequestLogDO(requestLogDO);
        errorRequestLogDO.setErrorMsg(errorMsg);
        errorRequestLogStore.saveAsync(errorRequestLogDO);
    }
}



