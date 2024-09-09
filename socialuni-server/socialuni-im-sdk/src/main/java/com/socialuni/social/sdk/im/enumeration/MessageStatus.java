package com.socialuni.social.sdk.im.enumeration;

import com.qingchi.qing.constant.SocialuniCommonStatus;

import java.util.Arrays;
import java.util.List;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class MessageStatus extends SocialuniCommonStatus {
    public static final List<String> allStatus = Arrays.asList(MessageStatus.enable);
}

