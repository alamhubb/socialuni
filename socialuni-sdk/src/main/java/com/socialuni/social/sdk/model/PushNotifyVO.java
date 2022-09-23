package com.socialuni.social.sdk.model;

import lombok.Data;

@Data
public class PushNotifyVO {
    //主体内容
    //content
    private PushValue content;
    //发送人
    private PushValue nickname;
    private PushValue date;
    //就是想评论
    private PushValue cause;
    //结果
    private PushValue result;
    //备注，
    private PushValue remark;
    //关联内容
    private PushValue beContent;
    //关联内容类型
    private PushValue beContentType;
    //关联内容发表人
    private PushValue beNickname;
}
