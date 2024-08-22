package com.socialuni.social.partner.config;

import com.socialuni.social.partner.constant.SocialuniPartnerPointsConsumeType;

import java.util.HashMap;
import java.util.Map;


public class SocialuniParterConfig {
    //积分存储默认倍数万
    public static final Integer pointDefaultMultiple = 10000;

    //总积分，1.1亿
    public static final Integer allPoints = 11000;

    //每日销毁数
    public static final Integer dayDestroy = 2;

    //使用共享分配
    public static final Integer useAssign = 70;
    //充值贡献分配
    public static final Integer payAssign = 30;

    //    发一句消息30贡献
    public static final Integer msgContributeValue = 30;
    //    听歌，1分钟10贡献
    public static final Integer musicContributeValue = 10;
    //    每日登录一下，100积分
    public static final Integer everyDayUseContributeValue = 100;
    //    连续使用贡献值倍数，每日增加倍数加成 value/100，每一天不登录，则掉相同的倍数加成
    public static final Integer everyDayUseMultipleAdditionContributeValue = 10;
    // 最大连续使用加成天数10天，最大贡献值翻倍
    public static final Integer maxContinuousUseDay = 10;

    // 充值的前1000名，可以获取当日贡献值加成， 第一名翻倍，前三1.6，前10，1.4，前100,1.2， 前1000,1.1
    // 使用贡献值计算时会，乘上充值加成， 充值贡献值计算时，仅使用充值加成
    // 使用贡献值计算公式， 贡献值*（1+连续使用贡献值加成(最大1) + 充值贡献值加成（最大1））
    // 充值贡献值计算公式， 充值贡献值*（1+ 充值贡献值加成（最大1））

    public static final Map<String, Integer> consumeTypeAssignValueMap = new HashMap<String, Integer>() {{
        put(SocialuniPartnerPointsConsumeType.payAssign, 30);
        put(SocialuniPartnerPointsConsumeType.useAssign, 70);
    }};


    public static final Map<String, Integer> monthPointsConfigMap = new HashMap<String, Integer>() {{
        //单位万
        put("2024-08", 30);
        put("2024-09", 63);
        put("2024-10", 45);
        put("2024-11", 45);
        put("2024-12", 30);
        put("2025-01", 30);
        put("2025-02", 30);
        put("2025-03", 21);
        put("2025-04", 21);
        put("2025-05", 21);
        put("2025-06", 18);
        put("2025-07", 18);
        put("2025-08", 18);
        put("2025-09", 18);
        put("2025-10", 18);
        put("2025-11", 18);
        put("2025-12", 15);
        put("2026-01", 15);
        put("2027-01", 6);
    }};

//初步计划，8月内测，每天分配30w积分
//2024年，9月，每日分配63w积分
//2024年，10月，每日分配45w积分
//2024年，11月，每日分配45w积分
//2024年，12月，每日分配30w积分
//2025年，1月，每日分配30w积分
//2025年，2月，每日分配30w积分
//2025年，3月，每日分配21w积分
//2025年，4月，每日分配21w积分
//2025年，5月，每日分配21w积分
//2025年，6月，每日分配18w积分
//2025年，7月，每日分配18w积分
//2025年，8月，每日分配18w积分
//2025年，9月，每日分配18w积分
//2025年，10月，每日分配18w积分
//2025年，11月，每日分配18w积分
//2025年，12月，每日分配15w积分
//2026年，1月，每日分配15w积分
//2026年，2月，每日分配15w积分
//2026年，3月，每日分配15w积分
//2026年，4月，每日分配15w积分
//2026年，5月，每日分配15w积分
//2026年，6月，每日分配12w积分
//2026年，7月，每日分配12w积分
//2026年，8月，每日分配12w积分
//2026年，9月，每日分配9w积分
//2026年，10月，每日分配9w积分
//2026年，11月，每日分配6w积分
//2026年，12月，每日分配6w积分
//2027年，1月，每日分配6w积分
//2027年，2月，每日分配6w积分
//2027年，3月，每日分配3w积分
//2027年，4月，每日分配3w积分
//2027年，5月，每日分配3w积分
//2027年，6月，每日分配3w积分
//2027年，7月，每日分配3w积分
//2027年，8月，每日分配3w积分
}
