package com.socialuni.social.tance.config;

import com.socialuni.social.common.api.constant.SocialSystemConst;
import com.socialuni.social.tance.controller.SocialuniAdminAppConfigController;
import com.socialuni.social.tance.repository.PublishDataTanceBaseRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    @Resource
    SocialuniAdminAppConfigController appConfigController;
    @Resource
    PublishDataTransactionalEventListener publishDataTransactionalEventListener;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 初始化。
        PublishDataTanceBaseRepository.publishDataInitialized(request);
        // 设置配置属性。
        Map<String, Object> allConfigsOfMap = appConfigController.getAllConfigsOfMap();
        request.setAttribute(SocialSystemConst.CONFIGS_REQUEST_NAME,allConfigsOfMap);
        //
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 没有异常才去同步。  有异常也不会进postHandle。
            publishDataTransactionalEventListener.publishDataToDev();
    }

}
