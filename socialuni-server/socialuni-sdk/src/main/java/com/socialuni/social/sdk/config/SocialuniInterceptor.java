package com.socialuni.social.sdk.config;

import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.expand.utils.SocialuniUserExpandDOUtil;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;

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

        Long userId = socialRequestUserConfig.getUserId();

        if (userId == null) {
            return true;
        }

        CompletableFuture.supplyAsync(() -> {
            SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userId);
            socialuniUserExpandDo.setLastOnlineTime(new Date());
            SocialuniUserExpandDOUtil.saveUserExpandDO(socialuniUserExpandDo);
            return null;
        });
        return true;
    }
}