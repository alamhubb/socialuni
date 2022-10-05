package com.socialuni.social.tance.config;

import com.socialuni.social.tance.repository.PublishDataTanceBaseRepository;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

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
public class TanceHandlerInterceptor extends SocialuniWebInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 初始化。
        PublishDataTanceBaseRepository.publishDataInitialized(request);
        //
        return super.preHandle(request, response, handler);
    }
}
