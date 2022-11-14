package com.socialuni.social.common.sdk.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WyCheckConst {
    public static Integer success = 0;
    public static Integer suspect = 1;
    public static Integer violate = 2;

    public final static List<Integer> violateTypes = Arrays.asList(suspect, violate);

    public static HashMap<Integer, String> actionMap = new HashMap<Integer, String>() {{
        put(success, "正常");
        put(suspect, "嫌疑");
        put(violate, "违规");
    }};

    public static HashMap<Integer, String> labelMap = new HashMap<Integer, String>() {{
        put(100, "色情");
        put(200, "广告");
        put(260, "广告法");
        put(300, "暴恐");
        put(400, "违禁");
        put(500, "涉政");
        put(600, "谩骂");
        put(700, "灌水");
        put(900, "其他");
    }};

    public static HashMap<Integer, String> subLabelMap = new HashMap<Integer, String>() {{
        put(100001, "色情其他");
        put(100002, "色情传播");
        put(100003, "色情性器官");
        put(100004, "色情挑逗");
        put(100005, "色情低俗段子");
        put(100006, "色情性行为");
        put(100007, "色情舆情事件");
        put(100008, "色情交友类");

        put(200009, "商业推广");
        put(200010, "广告法");
        put(200011, "刷量行为");
        put(200012, "广告其他");
        put(260052, "广告法-涉医疗用语（非药品禁止宣传药效）");
        put(260053, "广告法-迷信用语");
        put(260054, "广告法-需要凭证（可以写但需要凭证证明）");
        put(260055, "广告法-限时性用语（可以写但必须有具体时间）");
        put(260056, "广告法-涉嫌诱导消费者");
        put(260057, "广告法-涉嫌欺诈消费者");
        put(260058, "广告法-法律风险较高");
        put(260059, "广告法-极限词（用语绝对化）");

        put(300016, "暴恐其他");
        put(400017, "违禁其他");
        put(400021, "违禁网监要求");
        put(500013, "涉政其他");
        put(500070, "核心领导人");
        put(500014, "敏感专项");
        put(500015, "严格涉政");
        put(500039, "时事报道");
        put(500040, "领导人相关");
        put(500041, "英雄烈士相关");
        put(500042, "邪教迷信");
        put(500043, "落马官员相关");
        put(500044, "热点舆情");
        put(500045, "涉政综合");
        put(600018, "谩骂其他");
        put(700019, "灌水其他");
        put(900020, "其他");
    }};
}
