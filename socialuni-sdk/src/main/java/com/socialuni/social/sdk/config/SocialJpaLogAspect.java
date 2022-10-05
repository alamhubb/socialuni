package com.socialuni.social.sdk.config;

import com.socialuni.social.sdk.dao.DO.JpaSqlLogDO;
import com.socialuni.social.sdk.utils.JpaSqlLogDOUtil;
import com.socialuni.social.web.sdk.model.RequestLogDO;
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
public class SocialJpaLogAspect {
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
                jpaSqlLogDO.setRequestId(requestLogDO.getId());
            }
            log.info("[{}：{}],[spendTimes:{}]", jpaSqlLogDO.getRequestId(), interfaceAndMethodName, spendTime);

            JpaSqlLogDOUtil.saveAsync(jpaSqlLogDO);
        }
        return result;
    }
}