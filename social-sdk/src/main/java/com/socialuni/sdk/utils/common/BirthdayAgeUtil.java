package com.socialuni.sdk.utils.common;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-24 22:12
 */
public class BirthdayAgeUtil {
    public static void main(String[] args) throws ParseException {
        System.out.println(BirthdayAgeUtil.birthdayDateFormat.parse(""));
    }

    public static final SimpleDateFormat birthdayYearFormat = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat birthdayDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //根据出生日期，获取年龄
    public static int getAgeByBirth(String birthDayStr) {
        Date birthDay = new Date();
        try {
            if (StringUtils.isNotEmpty(birthDayStr)) {
                birthDay = BirthdayAgeUtil.birthdayDateFormat.parse(birthDayStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int age = 1;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            return age;
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    public static String getYearBirthDateByAge(Integer age) {
        Calendar cal = Calendar.getInstance();
        //获取当前年
        int yearNow = cal.getWeekYear();
        int birthYear = yearNow - age;
        if (birthYear < 1) {
            return BirthdayAgeUtil.birthdayYearFormat.format(cal.getTime());
        }
        return BirthdayAgeUtil.getYearBirthDateByDateStr(String.valueOf(birthYear));
    }

    //任何日期，转为年份-01-01
    public static String getYearBirthDateByDateStr(String dateStr) {
        try {
            return BirthdayAgeUtil.birthdayDateFormat.format(BirthdayAgeUtil.birthdayYearFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //把不合规的日期形势转为规范的日期形势1999转为1999-01-01
    public static String getBirthDateByDateStr(String dateStr) {
        try {
            return BirthdayAgeUtil.birthdayDateFormat.format(BirthdayAgeUtil.birthdayDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
