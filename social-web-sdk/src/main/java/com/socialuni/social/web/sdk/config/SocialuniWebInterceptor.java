package com.socialuni.social.web.sdk.config;


import com.socialuni.social.web.sdk.constant.ErrorType;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.IpUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
public class SocialuniWebInterceptor implements HandlerInterceptor {
    @Resource
    SocialuniWebInterceptorConfig socialuniWebInterceptorConfig;

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
//        requestLogDO.setRequestId(RequestIdUtil.addId());
        requestLogDO.setIp(userIp);
        requestLogDO.setCreateTime(startTime);
        requestLogDO.setSuccess(true);
        requestLogDO.setErrorType(ErrorType.success);
        requestLogDO.setRequestMethod(requestMethod);
        requestLogDO.setSystemInfo(RequestUtil.getSystem());
        requestLogDO.setProvider(RequestUtil.getProvider());
        requestLogDO.setPlatform(RequestUtil.getPlatform());
        requestLogDO.setUri(uri);
        RequestLogUtil.set(requestLogDO);

        requestLogDO.setUserId(socialuniWebInterceptorConfig.getUserId());
//        requestLogDO.setErrorCode(ResultRO.successCode);
//        requestLogDO.setErrorType(ErrorType.success);
//        requestLogDO.setErrorMsg(ErrorMsg.successMsg);
//        requestLogDO.setInnerMsg(ErrorMsg.successMsg);

        requestLogDO = RequestLogUtil.save(requestLogDO);
        return true;
    }
}