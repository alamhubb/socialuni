package com.socialuni.social.tance.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/1 16:26
 * @since 1.0
 */
@Component
@ConditionalOnProperty(
        name = {"api.tance.enable"},
        matchIfMissing = true
)
public class TanceHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
