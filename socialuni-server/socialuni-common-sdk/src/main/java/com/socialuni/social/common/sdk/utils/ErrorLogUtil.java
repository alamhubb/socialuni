package com.socialuni.social.common.sdk.utils;


import com.qingchi.qing.common.exception.base.ErrorCode;
import com.qingchi.qing.common.exception.base.QingExceptionErrorType;
import com.socialuni.social.common.sdk.dao.store.ErrorRequestLogStore;
import com.socialuni.social.common.api.dao.DO.ErrorRequestLogDO;
import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
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
        errorRequestLogDO.setErrorType(QingExceptionErrorType.error);
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
        errorRequestLogDO.setErrorType(QingExceptionErrorType.error);
        errorRequestLogDO.setErrorMsg(errorMsg);
        errorRequestLogDO.setEndTime(new Date());
        errorRequestLogStore.saveAsync(errorRequestLogDO);
    }
}



