package com.socialuni.social.sdk.constant.status;


import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.ConstStatus;

import java.util.Arrays;
import java.util.List;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class MessageReceiveStatus extends CommonStatus {
    public static final String waitOpen = ConstStatus.waitOpen;

    public static final List<String> allStatus = Arrays.asList(MessageReceiveStatus.enable);

   /* //启用
    public static final String normal = "正常";
    //删除
    public static final String delete = "已删除";
    //待开启，需要有一方发送一条消息后，才改为开启状态
    public static final String waitOpen = "待开启";
    //chat_user match相关
    public static final String waitMatch = "待匹配";*/
}

