package com.socialuni.social.web.sdk.config;

import com.socialuni.social.common.sdk.config.SocialWebControllerAdvice;
import com.socialuni.social.common.sdk.event.WebControllerExceptionEvent;
import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.constant.RequestErrorMsg;
import com.socialuni.social.common.api.constant.ErrorType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.IpUtil;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import com.socialuni.social.common.sdk.utils.ErrorLogUtil;
import com.socialuni.social.common.sdk.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

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
        // 解决异步报错。切面记录日志的问题。
        if (requestLogDO == null) {
            return joinPoint.proceed();
        }
        String params = Arrays.toString(joinPoint.getArgs());
        requestLogDO.setParams(params);
        // 日志不报错的问题。
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            Date endDate = new Date();
            long spendTime = endDate.getTime() - requestLogDO.getCreateTime().getTime();
            requestLogDO.setEndTime(endDate);
            requestLogDO.setSpendTime(spendTime);
            StringBuilder sb = new StringBuilder();
            if (result != null) {
                sb.append("result is not null");
                if (result instanceof ResultRO) {
                    ResultRO resultRO = (ResultRO) result;
                    requestLogDO.setErrorCode(resultRO.getCode());
                    requestLogDO.setErrorMsg(resultRO.getErrorMsg());
                    requestLogDO.setSuccess(resultRO.getSuccess());
                    sb.append(", instanceof ResultRO ");
                } else {
                    requestLogDO.setErrorCode(ErrorCode.SYSTEM_ERROR);
                    requestLogDO.setErrorType(ErrorType.error);
                    requestLogDO.setErrorMsg(RequestErrorMsg.getSystemErrorMsg());
                    requestLogDO.setSuccess(false);
                    requestLogDO.setInnerMsg(RequestErrorMsg.getSystemErrorMsg());
                    requestLogDO.setInnerMsgDetail(result.toString());
                    sb.append(",not of instanceof ResultRO ");
                    ErrorLogUtil.saveAsync(requestLogDO);
                }
            }
            log.info(sb.toString()); // 添加日志。
            RequestLogUtil.saveAsyncAndRemove(requestLogDO);
        }
        return result;
    }

    /**
     * @param event
     * @see SocialWebControllerAdvice#saveOperateLogDO(String, Integer, String, String, String)
     * @see WebControllerExceptionEvent
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
        //不为限制ip次数，才保存错误信息
        if (!ErrorCode.IP_LIMIT_ERROR.equals(requestLogDO.getErrorCode())) {
            ErrorLogUtil.saveAsync(requestLogDO);
        }
        log.info("[{}：{}],[{}({})][spendTimes:{}]", requestLogDO.getId(), requestLogDO.getErrorMsg(), requestLogDO.getRequestMethod(), requestLogDO.getUri(), spendTime);

    }
}