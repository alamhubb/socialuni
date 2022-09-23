package com.socialuni.social.sdk.constant.socialuni;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class StatusConst {
    //启用
    public static final String enable = "正常";

    //待开启，需要有一方发送一条消息后，才改为开启状态
    public static final String waitOpen = "待开启";

    public static final String close = "已关闭";

    public static final String beClose = "被关闭";

    public static final String delete = "已删除";

    //待审核
    public static final String preAudit = "预审核";

    //待审核
    public static final String audit = "审核中";

    //违规
    public static final String violation = "违规";
    public static final String init = "初始";

    public static final String success = "成功";//初始
    public static final String fail = "失败";//初始



    //不违规
//    public static final String noViolation = "不违规";
}

