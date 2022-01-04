package com.socialuni.center.web.config;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.entity.model.DO.JpaSqlLogDO;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.sdk.utils.JpaSqlLogDOUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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
        RequestLogUtil.saveAsyncAndRemove(requestLogDO);
        return result;
    }

    //获取方法类全名+方法名
    private String getInterfaceAndMethodName(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        //获取目标类对象
        Class<?> aClass = joinPoint.getTarget().getClass();
        //获取方法签名信息,方法名和参数列表
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取目标方法对象
        Method method = aClass.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        //获取接口名
        String interfaceName = aClass.getInterfaces()[0].getSimpleName();
        //获取方法名
        String methodName = method.getName();
        return new StringBuffer(interfaceName).append(".").append(methodName).toString();
    }

    @Pointcut("within(org.springframework.data.jpa.repository.JpaRepository+)")
    public void sqlRepositoryLog() {
    }

    @Around("sqlRepositoryLog()")
    public Object sqlRepositoryLogHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        JpaSqlLogDO jpaSqlLogDO = new JpaSqlLogDO();

        String interfaceAndMethodName = this.getInterfaceAndMethodName(joinPoint);

        Object result = joinPoint.proceed();

        // 不为写入日志，才保存，写入日志为自己，如果保存会无限循环
        if (interfaceAndMethodName.contains("Log")) {
            return result;
        }
        Date endDate = new Date();
        long spendTime = endDate.getTime() - jpaSqlLogDO.getCreateTime().getTime();
        //执行时间大于1秒的，才记录
        if (spendTime > 1000) {
            jpaSqlLogDO.setEndTime(endDate);
            jpaSqlLogDO.setSpendTime(spendTime);

            String params = Arrays.toString(joinPoint.getArgs());
            jpaSqlLogDO.setParams(params);

            jpaSqlLogDO.setInterfaceMethod(interfaceAndMethodName);

            RequestLogDO requestLogDO = RequestLogUtil.get();
            if (requestLogDO != null) {
                jpaSqlLogDO.setRequestId(requestLogDO.getRequestId());
            }
            log.info("[{}：{}],[spendTimes:{}]", jpaSqlLogDO.getRequestId(), interfaceAndMethodName, spendTime);

            JpaSqlLogDOUtil.saveAsync(jpaSqlLogDO);
        }
        return result;
    }
}