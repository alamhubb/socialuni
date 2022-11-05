package com.socialuni.social.sdk.utils.common;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-24 22:12
 */
public class BirthdayAgeUtil {
    public static final SimpleDateFormat birthdayYearFormat = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat birthdayDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getBirthDayByBirthString(String birthDayStr) {
        try {
            return BirthdayAgeUtil.birthdayDateFormat.parse(birthDayStr);
        } catch (ParseException e) {
            throw new SocialParamsException("用户生日格式错误");
        }
    }

    //根据出生日期，获取年龄
    public static int getAgeByBirth(String birthDayStr) {
        Date birthDay = new Date();
        try {
            if (StringUtils.isNotEmpty(birthDayStr)) {
                if (birthDayStr.length() < 6) {
                    birthDay = BirthdayAgeUtil.birthdayYearFormat.parse(birthDayStr);
                } else {
                    birthDay = BirthdayAgeUtil.birthdayDateFormat.parse(birthDayStr);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int age = 1;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            return age;
        }
        //+1,采用虚岁规则
        int yearNow = cal.get(Calendar.YEAR) + 1; //当前年份
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

    public static String getYearStrByAge(Integer age) {
        Calendar cal = Calendar.getInstance();
        //获取当前年
        int yearNow = cal.getWeekYear();
        int birthYear = yearNow - age;
        if (birthYear < 1) {
            return BirthdayAgeUtil.birthdayYearFormat.format(cal.getTime());
        }
        try {
            return BirthdayAgeUtil.birthdayYearFormat.format(BirthdayAgeUtil.birthdayYearFormat.parse(String.valueOf(birthYear)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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

    public static boolean lessThan18Str(String numStr) {
        if (numStr.length() == 2) {
            int age = Integer.parseInt(numStr);
            return age < 18;
        }
        return false;
    }

    /*public static String replaceAgeBetween10to18Str(String content) {
        //删除非数字、字母、汉字
        content = BirthdayAgeUtil.formatHanziNumContentOnlyEmptyChar(content);
        //定义正则表达式
        String reg = "\\d+";
        //编译正则表达式
        Pattern patten = Pattern.compile(reg);
        return StringUtil.replaceAll(content, patten, (result) -> BirthdayAgeUtil.getAgeBetween10to18Str(result.group()));
    }*/

    /*public static String formatHanziNumContentOnlyEmptyChar(String content) {
        //匹配非空格内容
        String regEx = "\\S";
        Pattern p = Pattern.compile(regEx);
        content = StringUtil.replaceAll(content, p, (result) -> {
            String resGroup = result.group();
            Integer hanziNum = ModelContentCheck.hanziNumberMap.get(resGroup);
            if (hanziNum != null) {
                return hanziNum.toString();
            }
            return resGroup;
        });
        //删除非数字、字母、汉字
        content = content.trim().replaceAll("[\\t ]", "");
        return content;
    }*/
}
