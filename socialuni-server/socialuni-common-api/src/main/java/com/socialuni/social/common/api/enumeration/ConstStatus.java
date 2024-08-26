package com.socialuni.social.common.api.enumeration;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class ConstStatus {
    //启用
    public static final String init = "初始";
    public static final String enable = "正常";
    public static final String apply = "申请";
    public static final String accept = "接受";
    public static final String refuse = "拒绝";
    public static final String expired = "过期";
//    public static final String agree = " 同意";

    //待开启，需要有一方发送一条消息后，才改为开启状态
    public static final String pending = "待处理";

    public static final String close = "已关闭";

    public static final String beClose = "被关闭";

    public static final String delete = "已删除";

    //待审核
    public static final String preAudit = "预审核";

    //待审核
    public static final String auditing = "审核中";

    //违规
    public static final String violation = "违规";
    public static final String success = "成功";
    public static final String fail = "失败";
    public static final String waitPay = "待支付";
    //不违规
//    public static final String noViolation = "不违规";
/*
    public static final Integer initNum = 0;
    public static final Integer useNearbyNum = 1;
    public static final Integer unUseNearbyNum = 2;
    public static final String waitPay = "待支付";//初始
    public static final String success = "成功";//初始
    public static final String fail = "失败";//初始
    public static final String init = "初始";//初始
public static final String preAudit = "预审核";//待审核
     public static final String audit = "审核中";//待审核
     public static final String enable = "正常";//启用
     public static final String delete = "已删除";//删除
     public static final String violation = "违规";//违规*//*

    public static final String authFail = "认证失败";//删除
    // 忘记了为什么要设置一个不违规状态，1，会扩大职责范围，而，2.存在审核错了，还可以让其他人举报，净化环境
    // 目前已知优点，能方便发现是审核过的不违规状态
    // 缺点，需要维护，审核记录吧，给谁发放了奖励，扣除了奖励，扣除和发放错了怎么办
    // 所以目前能做的就是，只修改report状态，不追溯之前的
//    public static final String noViolation = "不违规";//不违规
    public static final String lose = "失效";//失效
    public static final String destroy = "注销";//失效

    //message特有
    public static final String withdraw = "已撤回";//失效

    //chat特有

    //chat_user match相关
    public static final String waitMatch = "待匹配";
    //待开启，需要有一方发送一条消息后，才改为开启状态
//    public static final String waitOpen = "待开启";

    //消息专属
    //每次发送消息，查询所有状态为未发送的和此次的一起发送，成功则变成未读，
    //每次初始查询，或者进入chat页面时，查询chat中的所有msg，将所有未发送的变为未读
    //发送状态
    public static final String sending = "发送中";//未发送
    public static final String sended = "已发送";//未读
    public static final String read = "已读";//已读

    //阅读状态
    public static final String unread = "未读";//已读


    //只要是举报为审核中的所有都能查出来
    public static final List<String> contentAuditStatus = Arrays.asList(ContentStatus.enable, ContentStatus.preAudit, ContentStatus.audit, ContentStatus.delete);

    //todo 这个状态有问题，看引用
    public static final List<String> auditStatus = Arrays.asList(ContentStatus.preAudit, ContentStatus.audit);
    //可以发布内容的状态
    */
}

