package com.socialuni.social.admin.config;

import com.socialuni.social.admin.facede.AdminDevAccountFacade;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.sdk.utils.RedisUtil;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import com.socialuni.social.common.sdk.utils.RequestLogUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Primary
@Component
@Slf4j
public class WebInterceptor extends SocialuniWebInterceptor {
    @Resource
    private RedisUtil redisUtil;

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
        RequestLogDO requestLogDO = RequestLogUtil.get();
        DevAccountModel user = AdminDevAccountFacade.getAdminDevAccountAllowNull();
        if (user != null) {
            RequestUtil.setAttribute(SocialFeignHeaderName.socialuniSecretKey, user.getSecretKey());
        }
        RequestLogUtil.saveAsync(requestLogDO);
        String ipKey = requestLogDO.getIp();
        String uri = requestLogDO.getUri();

        if (SocialuniSystemConst.getIsProdEnv()) {
            //限制3分钟访问50次
            long expireTime = redisUtil.getExpire(ipKey);
            Object keyCountObj = redisUtil.get(ipKey);
            if (keyCountObj == null || expireTime < 0) {
                redisUtil.set(ipKey, 1, 180);
            } else {
                int count = (Integer) keyCountObj;
                if (count > 50) {
                    //永久封禁
                    redisUtil.set(ipKey, 100, DateTimeType.year / 1000);
                    //这里只提示未登录
                    res.setStatus(ErrorCode.IP_LIMIT_ERROR);
                    throw new SocialParamsException("访问频繁，请联系客服QQ:491369310");
                }
                //否则访问次数加1
                count = count + 1;
                redisUtil.set(ipKey, count, expireTime);
            }
        }

        if ((request.getMethod().equals(RequestMethod.OPTIONS.name())
                || uri.contains("openService")
                || uri.contains("phoneLogin")
                || uri.contains("secretKeyLogin")
                || uri.contains("sendAuthCode")
                || uri.contains("sendErrorLog")
                || uri.contains("getAppLaunchData")
                || uri.contains("queryHomeSwipers")
                || uri.contains("queryReportTypes")
                //这里只查询没被封禁的
                || user != null)
        ) {
//            if (user != null) {
//                req.setAttribute(AdminAppConfigConst.appUserKey, user);
//            }
            return true;
        }
        log.info("用户未登录");
        throw new SocialNotLoginException();
    }
}