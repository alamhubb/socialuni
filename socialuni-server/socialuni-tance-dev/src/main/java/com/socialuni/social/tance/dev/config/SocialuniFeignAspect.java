package com.socialuni.social.tance.dev.config;

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
import com.socialuni.social.tance.dev.api.SocialuniNoUseFeignAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Configuration
@Slf4j
public class SocialuniFeignAspect {
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
        // 获取目标对象
        Object target = joinPoint.getTarget();

        // 获取目标对象的类
        Class<?> targetClass = target.getClass();

        Boolean hasFlag = false;

        SocialuniNoUseFeignAspect feignAspect = targetClass.getAnnotation(SocialuniNoUseFeignAspect.class);

        if (feignAspect != null) {
            hasFlag = true;
        }
        if (!hasFlag) {
            // 获取方法签名
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            feignAspect = method.getAnnotation(SocialuniNoUseFeignAspect.class);

            if (feignAspect != null) {
                hasFlag = true;
            }
        }

        // 获取目标类实现的接口
        Class<?>[] interfaces = targetClass.getInterfaces();

        for (Class<?> interfaceClass : interfaces) {
            FeignClient feignClient = interfaceClass.getAnnotation(FeignClient.class);
            if (feignClient != null) {
                if (hasFlag) {
                    log.info("chufale you zidongchulide ");
                }
            }
        }

        // 执行目标方法
        Object result = joinPoint.proceed();

        return result;
    }
}