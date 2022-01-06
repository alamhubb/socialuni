package com.socialuni.center.web.config;

import com.socialuni.center.web.exception.SocialNullDevAccountException;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.exception.constant.ErrorType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.web.sdk.config.SocialWebRequestLogInterceptor;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.sdk.utils.RedisUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import com.socialuni.social.web.sdk.utils.IpUtil;
import com.socialuni.social.web.sdk.utils.RequestIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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

        if (
                (requestMethod.equals(RequestMethod.OPTIONS.name())
                        || uri.equals("/")
                        || uri.contains("test")
                        //初始化
                        || uri.contains("getAppLaunchData")
                        //初始化的请求
                        || uri.contains("report/queryReportTypes")
                        //查询首页轮播图
                        || uri.contains("queryHomeSwipers")
                        //同步生产环境数据到开发
                        || uri.contains("queryDevAccount")
                        || uri.contains("queryHotCircles")
                        || uri.contains("queryCircleTypes")
                        || uri.contains("admin/syncProdDevAccount")
                        //mock授权登录
                        || uri.contains("mockOAuthUserInfo")
                        || uri.contains("mockOAuthUserPhoneNum")
                        || uri.contains("tag/queryTags")
                        || uri.contains("tag/queryHotTags")
                        || uri.contains("tag/queryTagTypes")
                        || uri.contains("tag/queryHotTagType")
                        || uri.contains("/queryDistricts")
                        || uri.contains("/queryHotDistricts")
                        || uri.contains("/devUser/queryDevUserDetail")
                        || uri.contains("/app/sendErrorLog")
                        || uri.contains("/app/getAppConfig")
                        //渠道登录的方法
                        || uri.contains("queryProvinceDistricts")
                        || uri.contains("queryHotProvinceDistricts")
                        || uri.contains("queryOtherHomeTypeTalks")
                        || uri.contains("readChat")
                        || uri.contains("queryChats")
                        || uri.contains("refreshKeywords")
                        || uri.contains("refreshConfigMap")
                        || uri.contains("refreshRedis")
                        || uri.contains("qqPayNotify")
                        || uri.contains("wxPayNotify")
                        || uri.contains("app/checkUpdate")
                        || uri.contains("match/queryMatchUsers")
                        || uri.contains("authentication")
                        || uri.contains("match/queryUsers")
                        || uri.contains("talk/queryTalks")
                        || uri.contains("message/queryMessages")
                        || uri.contains("/webSocketServer")
                        || uri.contains("/myHandler")
                        || uri.contains("/queryUserTalks")
                        || uri.contains("/queryTalkDetail")
                        || uri.contains("/queryUserDetail")
                        || uri.contains("/queryAppInitData")
                        || uri.contains("/queryAppInitDataLoad")
                        || uri.contains("/queryAppInitDataLoad1")
                        || uri.contains("/queryAppInitDataReady")
                        || uri.contains("/img")
                        || uri.contains("/css")
                        //这里只查询没被封禁的
                        //有些接口必须有用户才可访问
                        || user != null
                        //有些接口必须有开发者账号才可访问
                        //或者为这些url，但divId不为null
                        || ((uri.contains("user/providerLogin")
                        || uri.contains("user/platformLogin")
                        || uri.contains("user/miniAppLogin")
                        || uri.contains("user/appLogin")
                        || uri.contains("appLogin")
                        || uri.contains("wxLogin")
                        || uri.contains("bindWxPhoneNum")
                        || uri.contains("bindPhoneNum")
                        || uri.contains("sendAuthCode")
                        || uri.contains("login")
                        || uri.contains("Login")
                        || uri.contains("location")
                        || uri.contains("oauth"))
                        && devId != null)
                )
                        //不允许读取idcard信息
                        && !uri.contains("/img/idCard")
        ) {
            return true;
        }
        /*else {
            String origin = request.getHeader("Origin");
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "*");
            res.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,token,X-Requested-With");
            res.setHeader("Access-Control-Allow-Credentials", "true");
            //这里只提示未登录
            res.setStatus(ErrorCode.NOT_LOGGED_ERROR);
            return false;
        }*/
        if (devId == null) {
            log.info("开发者为空");
            throw new SocialNullDevAccountException();
        } else if (user == null) {
            log.info("用户未登录");
            throw new SocialNotLoginException();
        } else {
            log.info("拦截器无法预见异常");
            throw new SocialSystemException("拦截器无法预见异常");
        }
    }
}