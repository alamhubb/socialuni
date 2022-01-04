package com.socialuni.demo.config;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.sdk.utils.RequestLogDOUtil;
import com.socialuni.social.sdk.utils.RequestLogUtil;
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
public class LogAspect {
    /**
     * 定义切入点，controller下面的所有类的所有公有方法，这里需要更改成自己项目的
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void requestLog() {
    }
    /*
     */

    /**
     * 方法之前执行，日志打印请求信息
     *
     * @param joinPoint joinPoint
     *//*
    @Before("requestLog()")
    public void requestBefore(JoinPoint joinPoint) {
        RequestLogDO operateLogDO = RequestLogUtil.get();
        String params = Arrays.toString(joinPoint.getArgs());
        operateLogDO.setParams(params);
    }*/
    @Around("requestLog()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestLogDO requestLogDO = RequestLogUtil.get();
//        Thread thread = Thread.currentThread();
//        HttpServletRequest httpServletRequest = RequestUtil.getRequest();
//        log.info("uri:{},thread:{},为null:{}", httpServletRequest.getRequestURI(), thread.getName(), requestLogDO == null);

        String params = Arrays.toString(joinPoint.getArgs());
        requestLogDO.setParams(params);

        Object result = joinPoint.proceed();

//        log.info("uri:{},thread:{},为null:{}", httpServletRequest.getRequestURI(), thread.getName(), requestLogDO == null);

        Date endDate = new Date();
        long spendTime = endDate.getTime() - requestLogDO.getCreateTime().getTime();
        requestLogDO.setEndTime(endDate);
        requestLogDO.setSpendTime(spendTime);
        if (result != null) {
            if (result instanceof ResultRO) {
                ResultRO resultRO = (ResultRO) result;
                requestLogDO.setErrorCode(resultRO.getErrorCode());
                requestLogDO.setErrorMsg(resultRO.getErrorMsg());
                requestLogDO.setSuccess(resultRO.getSuccess());
            } else {
                requestLogDO.setErrorCode(ErrorCode.SYSTEM_ERROR);
                requestLogDO.setErrorMsg("系统异常");
                requestLogDO.setSuccess(false);
                requestLogDO.setInnerMsg("系统异常");
                requestLogDO.setInnerMsgDetail(result.toString());
            }
        }
        log.info("[{}：{}],[{}({})][spendTimes:{}]", requestLogDO.getRequestId(), requestLogDO.getErrorMsg(), requestLogDO.getRequestMethod(), requestLogDO.getUri(), spendTime);

        RequestLogDOUtil.saveAsync(requestLogDO);
        return result;
    }
}