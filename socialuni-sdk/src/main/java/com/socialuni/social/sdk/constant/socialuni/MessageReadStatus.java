package com.socialuni.social.sdk.constant.socialuni;

import com.socialuni.social.common.api.enumeration.CommonStatus;

import java.util.Arrays;
import java.util.List;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class MessageReadStatus extends CommonStatus {
    public static final List<String> allStatus = Arrays.asList(enable);

    public static final String sending = "发送中";//未发送
    public static final String sended = "已发送";//未读
    public static final String read = "已读";//已读

    //阅读状态
    public static final String unread = "未读";//已读
}

