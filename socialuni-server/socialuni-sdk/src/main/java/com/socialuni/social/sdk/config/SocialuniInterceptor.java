package com.socialuni.social.sdk.config;

import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.facade.SocialuniUserRepositoryFacede;
import com.socialuni.social.sdk.utils.RedisUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.model.DO.RequestLogDO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.utils.RequestLogUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class SocialuniInterceptor extends SocialuniWebInterceptor {
    @Resource
    SocialRequestUserConfig socialRequestUserConfig;

    /*
     * 进入controller层之前拦截请求
     * 在请求处理之前进行调用（Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse res, Object o) {
        super.preHandle(request, res, o);
        String requestMethod = request.getMethod();

        if (requestMethod.equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        Integer userId = socialRequestUserConfig.getUserId();

        if (userId == null) {
            return true;
        }

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(socialRequestUserConfig.getUserId());
        socialuniUserExpandDo.setLastOnlineTime(new Date());

        CompletableFuture.supplyAsync(() -> {
            SocialuniUserExpandDOUtil.saveUserExpandDO(socialuniUserExpandDo);
            return null;
        }).exceptionally(e -> {
            e.printStackTrace();
            log.info(e.getMessage());
            return null;
        });
        return true;
    }
}