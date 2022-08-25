package com.socialuni.social.web.sdk.config;

import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.social.web.sdk.constant.ErrorCode;
import com.socialuni.social.web.sdk.constant.ErrorType;
import com.socialuni.social.web.sdk.constant.ErrorMsg;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.ErrorLogUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class SocialWebLogAspect {
    /**
     * 定义切入点，controller下面的所有类的所有公有方法，这里需要更改成自己项目的
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void requestLog() {
    }

    /**
     * 方法之前执行，日志打印请求信息
     *
     * @param joinPoint joinPoint
     */
    @Around("requestLog()")
    public Object requestLogHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestLogDO requestLogDO = RequestLogUtil.get();
        String params = Arrays.toString(joinPoint.getArgs());
        requestLogDO.setParams(params);

        Object result = joinPoint.proceed();

        Date endDate = new Date();
        long spendTime = endDate.getTime() - requestLogDO.getCreateTime().getTime();
        requestLogDO.setEndTime(endDate);
        requestLogDO.setSpendTime(spendTime);
        if (result != null) {
            if (result instanceof ResultRO) {
                ResultRO resultRO = (ResultRO) result;
                requestLogDO.setErrorCode(resultRO.getCode());
                requestLogDO.setErrorMsg(resultRO.getErrorMsg());
                requestLogDO.setSuccess(resultRO.getSuccess());
            } else {
                requestLogDO.setErrorCode(ErrorCode.SYSTEM_ERROR);
                requestLogDO.setErrorType(ErrorType.error);
                requestLogDO.setErrorMsg(ErrorMsg.systemErrorMsg);
                requestLogDO.setSuccess(false);
                requestLogDO.setInnerMsg(ErrorMsg.systemErrorMsg);
                requestLogDO.setInnerMsgDetail(result.toString());
                ErrorLogUtil.saveAsync(requestLogDO);
            }
        }
        RequestLogUtil.saveAsyncAndRemove(requestLogDO);
        return result;
    }
}