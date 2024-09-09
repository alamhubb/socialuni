package com.socialuni.social.common.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class SocialuniNotifyRO implements Serializable {
    //信息用来在前台展示消息内容简介
//    private SocialuniUserRO user;
    private List<String> userIds;
//    private String receiveUserId;
    //一种操作，一种消息，一种通知
    private String type;
    private Object data;
}
