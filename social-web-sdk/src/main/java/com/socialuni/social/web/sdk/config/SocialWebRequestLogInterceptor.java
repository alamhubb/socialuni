package com.socialuni.social.web.sdk.config;


import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@ConditionalOnMissingBean(SocialWebRequestLogInterceptor.class)
@Slf4j
public class SocialWebRequestLogInterceptor implements HandlerInterceptor {

    /*
     * 进入controller层之前拦截请求
     * 在请求处理之前进行调用（Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse res, Object o) {
        String requestMethod = request.getMethod();

        if (requestMethod.equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        Date startTime = new Date();

        String uri = request.getRequestURI();
        String userIp = IpUtil.getIpAddr(request);

        RequestLogDO requestLogDO = new RequestLogDO();
        requestLogDO.setRequestId(RequestIdUtil.addId());

        requestLogDO.setIp(userIp);
        requestLogDO.setCreateTime(startTime);
        requestLogDO.setSuccess(true);
        requestLogDO.setRequestMethod(requestMethod);
        requestLogDO.setUri(uri);


//        requestLogDO.setErrorCode(ResultRO.successCode);
//        requestLogDO.setErrorType(ErrorType.success);
//        requestLogDO.setErrorMsg(ErrorMsg.successMsg);
//        requestLogDO.setInnerMsg(ErrorMsg.successMsg);

        requestLogDO = RequestLogUtil.save(requestLogDO);
        RequestLogUtil.set(requestLogDO);
        return true;
    }
}