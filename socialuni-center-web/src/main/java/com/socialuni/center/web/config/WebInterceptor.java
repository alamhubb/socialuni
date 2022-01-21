package com.socialuni.center.web.config;

import com.socialuni.center.web.exception.SocialNullDevAccountException;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.RedisUtil;
import com.socialuni.social.web.sdk.config.SocialWebRequestLogInterceptor;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class WebInterceptor extends SocialWebRequestLogInterceptor {
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
        UserDO user = CenterUserUtil.getMineUserInterceptor();
        Integer userId = CenterUserUtil.getMineUserIdInterceptor();

        requestLogDO.setUserId(userId);
        Integer devId = DevAccountUtils.getDevIdAllowNull();
        requestLogDO.setDevId(devId);
        RequestLogUtil.saveAsync(requestLogDO);
        String userIp = requestLogDO.getIp();
        String uri = requestLogDO.getUri();

        String ipKey = "ipKey:" + userIp;

        /*Integer ipCount = AppStaticData.getIpCount(userIp);
        也可以采用map处理，ip可以value时间戳列表
        //获取验证码超过10次，则联系客服
        if (ipCount > 1000) {
            throw new SocialParamsException("访问频繁，请联系客服处理");
        }
        AppStaticData.ipCountPlus(userIp);*/

        if (devId == null) {
            Object keyCountObj = redisUtil.get(ipKey);
            //如果已经有了赋值
            if (keyCountObj != null) {
                int count = (Integer) keyCountObj;
                //限制1分钟访问50次
                if (count > 3000) {
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
        }

        if (user != null) {
            return true;
        }
        for (String whiteUrl : WhiteUrlListConfig.AppWhiteUrlList) {
            if (uri.contains(whiteUrl)) {
                return true;
            }
        }
        for (String whiteUrl : WhiteUrlListConfig.needDevIdUrlList) {
            if (uri.contains(whiteUrl) && devId != null) {
                return true;
            }
        }
        if (devId == null) {
            log.info("开发者为空");
            throw new SocialNullDevAccountException();
        }
        log.info("用户未登录");
        throw new SocialNotLoginException();
    }
}