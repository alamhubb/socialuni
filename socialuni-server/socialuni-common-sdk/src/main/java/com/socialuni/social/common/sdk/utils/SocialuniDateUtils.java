package com.socialuni.social.common.sdk.utils;


import com.socialuni.social.common.api.constant.DateTimeType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-03-17 3:26
 */
public class SocialuniDateUtils {
    public static final SimpleDateFormat simpleYearFormat = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat simpleMonthFormat = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat simpleSlashDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat timeStrFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static  String getToday() {
        Date curDate = new Date();
        String today = SocialuniDateUtils.simpleDateFormat.format(curDate);
        return today;
    }

    public static Date getTodayZeroDate() {
        //每天0点到现在不能发布超过10条
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Integer HowManyDaysFromNow(Date date) {
        //需要动态拼
        long endTime = date.getTime();
        long nowTime = new Date().getTime();
        //无法整除，所以+1
        long manyDay = (endTime - nowTime) / DateTimeType.day + 1;
        return Math.toIntExact(manyDay);
    }

    public static String getYearBirthDateByDateStr(String dateStr) {
        try {
            return SocialuniDateUtils.simpleDateFormat.format(SocialuniDateUtils.simpleYearFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBirthDateByDateStr(String dateStr) {
        try {
            return SocialuniDateUtils.simpleDateFormat.format(SocialuniDateUtils.simpleDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBirthDateBySlashDateStr(String dateStr) {
        try {
            return SocialuniDateUtils.simpleDateFormat.format(SocialuniDateUtils.simpleSlashDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeStrFormat(Date date) {
        return SocialuniDateUtils.timeStrFormat.format(date);
    }
}
