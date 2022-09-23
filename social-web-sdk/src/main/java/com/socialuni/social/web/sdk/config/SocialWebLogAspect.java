package com.socialuni.social.web.sdk.config;

import com.socialuni.social.common.config.SocialWebControllerAdvice;
import com.socialuni.social.common.event.WebControllerExceptionEvent;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.common.constant.ErrorCode;
import com.socialuni.social.common.constant.ErrorType;
import com.socialuni.social.common.constant.ErrorMsg;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.ErrorLogUtil;
import com.socialuni.social.common.utils.IpUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import com.socialuni.social.common.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Configuration
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

    /**
     * @see SocialWebControllerAdvice#saveOperateLogDO(String, Integer, String, String, String)
     * @see WebControllerExceptionEvent
     * @param event
     */
    @EventListener(classes = {WebControllerExceptionEvent.class})
    public void listen(WebControllerExceptionEvent event) {
//        System.out.println("注解事件触发：" + event.getClass().getName());

        String errorMsg = event.getErrorMsg();
        Integer errorCode = event.getErrorCode();
        String errorType = event.getErrorType();
        String innerMsg = event.getInnerMsg();
        String innerMsgDetail = event.getInnerMsgDetail();

        RequestLogDO requestLogDO = RequestLogUtil.get();
        if (requestLogDO == null) {
            HttpServletRequest request = RequestUtil.getRequest();
            Date startTime = new Date();
            String uri = request.getRequestURI();
            String userIp = IpUtil.getIpAddr(request);
            requestLogDO = new RequestLogDO();
            requestLogDO.setIp(userIp);
            requestLogDO.setCreateTime(startTime);
            requestLogDO.setSuccess(true);
            requestLogDO.setErrorType(ErrorType.success);
            requestLogDO.setRequestMethod(request.getMethod());
            requestLogDO.setSystemInfo(RequestUtil.getSystem());
            requestLogDO.setProvider(RequestUtil.getProvider());
            requestLogDO.setPlatform(RequestUtil.getPlatform());
            requestLogDO.setUri(uri);
        }
        Date endDate = new Date();
        long spendTime = endDate.getTime() - requestLogDO.getCreateTime().getTime();
        requestLogDO.setSuccess(false);
        requestLogDO.setErrorCode(errorCode);
        requestLogDO.setErrorMsg(errorMsg);
        requestLogDO.setErrorType(errorType);
        requestLogDO.setInnerMsg(innerMsg);
        requestLogDO.setInnerMsgDetail(innerMsgDetail);
        requestLogDO.setEndTime(endDate);
        requestLogDO.setSpendTime(spendTime);

        RequestLogUtil.saveAsyncAndRemove(requestLogDO);
        ErrorLogUtil.saveAsync(requestLogDO);

        log.info("[{}：{}],[{}({})][spendTimes:{}]", requestLogDO.getId(), requestLogDO.getErrorMsg(), requestLogDO.getRequestMethod(), requestLogDO.getUri(), spendTime);

    }
}