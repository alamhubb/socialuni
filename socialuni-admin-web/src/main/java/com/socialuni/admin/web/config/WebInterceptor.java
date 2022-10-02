package com.socialuni.admin.web.config;

import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.utils.RedisUtil;
import com.socialuni.social.common.constant.ErrorCode;
import com.socialuni.social.common.exception.exception.SocialNotLoginException;
import com.socialuni.social.web.sdk.config.SocialuniWebInterceptor;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
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
        log.info("进入拦截111111器：" + request.getRequestURI());
        super.preHandle(request, res, o);
        String requestMethod = request.getMethod();

        if (requestMethod.equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        RequestLogDO requestLogDO = RequestLogUtil.get();
        log.info("进入拦2222222截器：" + request.getRequestURI());
        DevAccountModel user = DevAccountFacade.getAdminDevAccountAllowNull();
        if (user != null) {
            requestLogDO.setUserId(user.getId());
        }
        RequestLogUtil.saveAsync(requestLogDO);
        String ipKey = requestLogDO.getIp();
        String uri = requestLogDO.getUri();

        Object keyCountObj = redisUtil.get(ipKey);
        //如果已经有了赋值
        if (keyCountObj != null) {
            int count = (Integer) keyCountObj;
            //限制1分钟访问50次
            if (count > 200) {
                //这里只提示未登录
                res.setStatus(ErrorCode.IP_LIMIT_ERROR);
                return false;
            }
            long expireTime = redisUtil.getExpire(ipKey);
            //否则访问次数加1
            count = count + 1;
            redisUtil.set(ipKey, count, expireTime);
        } else {
            redisUtil.set(ipKey, 1, 60);
        }


        if ((request.getMethod().equals(RequestMethod.OPTIONS.name())
                || uri.contains("openService")
                || uri.contains("user/phoneLogin")
                || uri.contains("user/secretKeyLogin")
                || uri.contains("phone/sendAuthCode")
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