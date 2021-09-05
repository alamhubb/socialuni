package com.qingchi.web.constant;


import com.socialuni.social.constant.DateTimeType;

/**
 * @author qinkaiyuan
 * @date 2019-06-14 20:00
 */
public class MatchConstants {
    //颜值百分比基础值
    public static final Integer FACE_RATIO_BASE_VALUE = 75;

    //颜值百分比随机范围
    public static final Integer FACE_RATIO_RANDOM_RANGE = 10;
    //qcb和颜值比
    public static final Integer QCB_FACE_VALUE_RATIO = 10;

    //颜值百分比基础倍数
    public static final Integer FACE_RATIO_BASE_MULTIPLE = 1000;

    //允许查看比自己高20分的人 20*1000
    public static final Integer ALLOW_HIGH_USER_FACE = 20 * FACE_RATIO_BASE_MULTIPLE;

    //颜值 认证加分 30
    public static final Integer FACE_VALUE_APPEND_MULTIPLE = 30 * FACE_RATIO_BASE_MULTIPLE;
    //颜值 普通会员加分 20
    public static final Integer FACE_VALUE_VIP_APPEND_MULTIPLE = 10 * FACE_RATIO_BASE_MULTIPLE;
    //颜值 年会员加分 30
    public static final Integer FACE_VALUE_YEAR_VIP_APPEND_MULTIPLE = 30 * FACE_RATIO_BASE_MULTIPLE;

    //颜值 满分100分
    public static final Long FACE_VALUE_BASE_MULTIPLE = (long) (100 * FACE_RATIO_BASE_MULTIPLE);

    //默认被多少人看过后开启真正颜值计算，因用户量太少，为提高用户体验，所有人都可以查看
    public static final Integer FACE_RATIO_OPEN_NUM = 100000;
    // 初始次数
    public static final Integer INIT_FACE_SEE_NUM = 5;

    //未成功匹配多少分钟，自动取消
    public static final Integer MATCH_UNLOCKED_CANCEL_MINUTE = 5;

    //成功匹配后多少分钟不说话，自动取消
    public static final Integer MATCH_CANCEL_HOUR = 5;
    //俩人多长时间不说话，解除惩罚双方
    public static final Long MINUTE_5 = 5 * DateTimeType.minute;
    public static final Long MINUTE_15 = 15 * DateTimeType.minute;
    public static final Long MINUTE_30 = 30  * DateTimeType.minute;
    public static final Long HOUR_2 = 2 * DateTimeType.hour;
}
