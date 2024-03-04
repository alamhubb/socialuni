package com.socialuni.social.common.sdk.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class ViolateType {
    /*todo 下一个版本的提示内容
    public final static String noViolation = "没有违规";
    public final static String pornInfo = "涉污涉黄";
    public final static String unfriendly = "不友善行为";
    public final static String badAd = "不良广告";
    public final static String userInfo = "头像或昵称违规";
    public final static String lawbreaking = "违法违规";
    public final static String swindle = "欺骗诈骗";
    public final static String other = "其他违规";
    */
    public final static String pornInfo = "涉污涉黄";
    public final static String sexy = "性感过度";
    public final static String unfriendly = "不友善行为";
    public final static String badAd = "骚扰广告";
    public final static String lawbreaking = "违法违规";
    public final static String other = "其他违规";


    public final static String userInfo = "用户头像或昵称违规";
    public final static String swindle = "欺骗诈骗";


    public final static List<String> frontShowReportTypes = Arrays.asList(pornInfo, sexy, unfriendly, badAd, lawbreaking, other);
}
