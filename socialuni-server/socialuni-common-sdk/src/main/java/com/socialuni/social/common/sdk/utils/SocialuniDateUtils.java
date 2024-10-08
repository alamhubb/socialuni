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

    public static String getToday() {
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

    public static String formatRemainingTime(long targetTime) {
        // 获取当前时间
        long currentTime = System.currentTimeMillis();

        // 计算时间差（毫秒）
        long diffTime = targetTime - currentTime;

        // 如果时间差小于0，说明目标时间已过
        if (diffTime <= 0) {
            return "0分";
        }

        // 将毫秒转换为秒、分、小时、天
        long diffSeconds = diffTime / 1000 % 60;
        long diffMinutes = diffTime / (60 * 1000) % 60;
        long diffHours = diffTime / (60 * 60 * 1000) % 24;
        long diffDays = diffTime / (24 * 60 * 60 * 1000);

        //格式化输出
        return String.format("%d天%d时%d分", diffDays, diffHours, diffMinutes);
    }

}
