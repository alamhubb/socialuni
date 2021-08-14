package com.socialuni.center.web.config;

import com.socialuni.social.constant.ErrorCode;
import com.socialuni.social.entity.model.DO.OperateLogDO;
import com.socialuni.social.sdk.utils.RequestLogDOUtil;
import com.socialuni.social.sdk.utils.RequestLogUtil;
import com.socialuni.social.api.model.ResultRO;
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
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) ||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)")
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
        OperateLogDO operateLogDO = RequestLogUtil.get();
        String params = Arrays.toString(joinPoint.getArgs());
        operateLogDO.setParams(params);
    }*/
    @Around("requestLog()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        OperateLogDO operateLogDO = RequestLogUtil.getAndRemove();
        String params = Arrays.toString(joinPoint.getArgs());
        operateLogDO.setParams(params);

        Object result = joinPoint.proceed();

        Date endDate = new Date();
        long spendTime = endDate.getTime() - operateLogDO.getCreateTime().getTime();
        operateLogDO.setEndTime(endDate);
        operateLogDO.setSpendTime(spendTime);
        if (result != null) {
            if (result instanceof ResultRO) {
                ResultRO resultRO = (ResultRO) result;
                operateLogDO.setErrorCode(resultRO.getErrorCode());
                operateLogDO.setErrorMsg(resultRO.getErrorMsg());
                operateLogDO.setSuccess(resultRO.getSuccess());
            } else {
                operateLogDO.setErrorCode(ErrorCode.SYSTEM_ERROR);
                operateLogDO.setErrorMsg("系统异常");
                operateLogDO.setSuccess(false);
                operateLogDO.setInnerMsg("系统异常");
                operateLogDO.setInnerMsgDetail(result.toString());
            }
        }
        log.info("[requestId:{},{}],[{}({})][spendTimes:{}]", operateLogDO.getId(), operateLogDO.getErrorMsg(), operateLogDO.getRequestMethod(), operateLogDO.getUri(), spendTime);
        RequestLogDOUtil.saveAsync(operateLogDO);
        return result;
    }
}