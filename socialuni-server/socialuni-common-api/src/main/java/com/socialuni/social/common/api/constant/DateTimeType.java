package com.socialuni.social.common.api.constant;

/**
 * @author qinkaiyuan
 * @date 2019-06-22 22:23
 */

//什么算是公共常量，就是放到任何项目都可以用的
public class DateTimeType {
    public static final Long second = (long) 1000;
    public static final Long minute = (long) 60 * second;
    public static final Long halfHour = (long) 30 * minute;
    public static final Long hour = (long) 60 * minute;
    public static final Long day = (long) 24 * hour;
    public static final Long week = (long) 7 * day;
    public static final Long month = (long) 31 * day;
    public static final Long quarter = (long) 3 * month;
    public static final Long halfYear = (long) 6 * month;
    public static final Long year = (long) 12 * month;
}
