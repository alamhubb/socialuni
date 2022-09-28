package com.socialuni.social.sdk.constant.socialuni;

import com.socialuni.social.common.enumeration.CommonStatus;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class ChatUserStatus extends CommonStatus {
    //enable 只有enable时可以发送消息，其他状态必须先转为开启才能发送消息

    //1，如果为关闭，则显示发起开启，和待开启一样，只要状态不为代开起，就没有pay
    //2.如果为代开起，付费，则需要点击开启，或者发送时提示是否要给对方发送并开启会话
    //3. 如果为被关闭，则不显示，发送消息报错

    //    chatuser 待开启，启用，已解除、被解除、，查所有状态的chatUser
//    哪些状态可开启，待开启，和 自己是 已解除，你发送消息，则开启，会将自己的状态改为，启用，
    public static final String waitOpen = ConstStatus.waitOpen;
    //自己主动关闭会话，关闭会话，删除会话
    public static final String close = ConstStatus.close;
    //自己被对方关闭，或者双方都主动关闭后，一方恢复，也为被关闭
    public static final String beClose = ConstStatus.beClose;

//    public static final List<String> allStatus = Arrays.asList(ChatStatus.enable, ChatUserStatus.waitOpen);

   /* //启用
    public static final String normal = "正常";
    //删除
    public static final String delete = "已删除";
    //待开启，需要有一方发送一条消息后，才改为开启状态
    public static final String waitOpen = "待开启";
    //chat_user match相关
    public static final String waitMatch = "待匹配";*/
}

