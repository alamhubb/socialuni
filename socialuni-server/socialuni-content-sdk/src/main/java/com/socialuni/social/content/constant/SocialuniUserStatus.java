package com.socialuni.social.content.constant;


import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.enumeration.ConstStatus;

import java.util.Arrays;
import java.util.List;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class SocialuniUserStatus extends SocialuniCommonStatus {
    //审核中
    public static final String audit = ConstStatus.auditing;
    //违规
    public static final String violation = ConstStatus.violation;
//    public static final String waitOpen = CommonStatus.waitOpen;

    public static final List<String> allStatus = Arrays.asList(SocialuniUserStatus.enable);
//    public static final List<String> allStatus = Arrays.asList(Status.enable, Status.waitOpen);

   /* //启用
    public static final String normal = "正常";
    //删除
    public static final String delete = "已删除";
    //待开启，需要有一方发送一条消息后，才改为开启状态
    public static final String waitOpen = "待开启";
    //chat_user match相关
    public static final String waitMatch = "待匹配";*/
}

