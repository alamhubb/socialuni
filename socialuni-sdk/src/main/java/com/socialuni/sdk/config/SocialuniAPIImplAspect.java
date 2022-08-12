package com.socialuni.sdk.config;

import com.socialuni.sdk.model.DO.JpaSqlLogDO;
import com.socialuni.sdk.utils.JpaSqlLogDOUtil;
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
public class SocialuniAPIImplAspect {
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

    @Pointcut("execution(* com.socialuni.sdk.api..*.*(..))")
    public void sqlRepositoryLog() {
    }

    @Around("sqlRepositoryLog()")
    public Object sqlRepositoryLogHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        JpaSqlLogDO jpaSqlLogDO = new JpaSqlLogDO();

        String interfaceAndMethodName = this.getInterfaceAndMethodName(joinPoint);

        Object result = joinPoint.proceed();

        System.out.println(interfaceAndMethodName);
        return result;
    }
}