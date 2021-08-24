package com.socialuni.social.web.sdk.utils;

import com.socialuni.social.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 当前线程的request工具类，从request中获取内容
 */
public class RequestIdUtil {
    static int i = 0;

    public static synchronized int addId() {
        ++i;
        return i;
    }

    public static void main(String[] args) {
        Date curDate = new Date();
//        log.info("创建uniond1：" + SystemUtil.getCurrentTimeSecond());
        //如果这两个都不为空则查找是否存在有效的
        //每天0点到现在不能发布超过10条
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println(minute);
        int ageMinuteTen = minute / 10;
        calendar.set(Calendar.MINUTE, ageMinuteTen * 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
        Date lastTenMinutes = calendar.getTime();

        System.out.println(lastTenMinutes);
        System.out.println(lastTenMinutes.getTime());
    }
}
