package com.socialuni.social.web.sdk.utils;


import com.socialuni.social.web.sdk.constant.ErrorCode;
import com.socialuni.social.web.sdk.constant.ErrorType;
import com.socialuni.social.web.sdk.model.ErrorRequestLogDO;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.store.ErrorRequestLogStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ErrorLogUtil {
    private static ErrorRequestLogStore errorRequestLogStore;

    @Resource
    public void setErrorRequestLogStore(ErrorRequestLogStore errorRequestLogStore) {
        ErrorLogUtil.errorRequestLogStore = errorRequestLogStore;
    }

    //系统根据error自动生成的
    public static void save(ErrorRequestLogDO errorRequestLogDO) {
        errorRequestLogDO.setSuccess(false);
        errorRequestLogDO.setErrorCode(ErrorCode.SYSTEM_ERROR);
        errorRequestLogDO.setErrorType(ErrorType.error);
        errorRequestLogDO.setEndTime(new Date());
        errorRequestLogStore.save(errorRequestLogDO);
    }

    //系统根据error自动生成的
    public static void saveAsync(RequestLogDO requestLogDO) {
        ErrorRequestLogDO errorRequestLogDO = new ErrorRequestLogDO(requestLogDO);
        errorRequestLogStore.saveAsync(errorRequestLogDO);
    }

    //后端直接生成的
    public static void error(String errorMsg) {
        RequestLogDO requestLogDO = RequestLogUtil.get();
        ErrorRequestLogDO errorRequestLogDO= new ErrorRequestLogDO(requestLogDO);

        errorRequestLogDO.setSuccess(false);
        errorRequestLogDO.setErrorCode(ErrorCode.BUSINESS_ERROR);
        errorRequestLogDO.setErrorType(ErrorType.error);
        errorRequestLogDO.setErrorMsg(errorMsg);
        errorRequestLogDO.setEndTime(new Date());
        errorRequestLogStore.saveAsync(errorRequestLogDO);
    }
}



