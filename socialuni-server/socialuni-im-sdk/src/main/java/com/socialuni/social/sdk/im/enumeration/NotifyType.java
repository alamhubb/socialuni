package com.socialuni.social.sdk.im.enumeration;

import java.util.Arrays;
import java.util.List;

/**
 * 关系类型枚举
 *
 * @author qinkaiyuan
 * @date 2018-09-16 10:58
 */
public class NotifyType {
    //系统
    public static final String system = "system";
    public static final String remove_chat = "remove_chat";

    //动态
    //兼容之前的内容2个版本以后可以删除，也不一定，删了以前的通知看不了，看看过几个版本吧
    public static final String comment = "comment";
    public static final String music = "music";
    //动态
    public static final String talk_comment = "talk_comment";
    //评论
    public static final String comment_comment = "comment_comment";
    public static final String reply_comment = "reply_comment";

    //举报
    public static final String report_result = "report_result";

    //违规
    public static final String violation = "violation_result";

    //消息
    public static final String message = "message";

    public static final List<String> comments = Arrays.asList(comment, talk_comment, comment_comment, reply_comment);

    public static final List<String> notifies = Arrays.asList(talk_comment, comment_comment, reply_comment, report_result, message, violation);
}

