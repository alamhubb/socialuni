package com.socialuni.admin.web.config;

import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.constant.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object arg2, Exception arg3) {
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) {
    }


    /*
     * 进入controller层之前拦截请求
     * 在请求处理之前进行调用（Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) {
        DevAccountDO user = devaccount.getUserByToken();

        String uri = req.getRequestURI();

        if ((req.getMethod().equals(RequestMethod.OPTIONS.name())
                || uri.contains("user/login")
                || uri.contains("phone/sendAuthCode")
                //这里只查询没被封禁的
                || user != null)
        ) {
//            if (user != null) {
//                req.setAttribute(AppConfigConst.appUserKey, user);
//            }
            return true;
        } else {
            String origin = req.getHeader("Origin");
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "*");
            res.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,token,X-Requested-With");
            res.setHeader("Access-Control-Allow-Credentials", "true");
            //这里只提示未登录
            res.setStatus(ErrorCode.NOT_LOGGED_ERROR);
            return false;
        }
    }
}