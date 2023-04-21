package com.socialuni.social.im.api.model.RO;

import lombok.Data;

@Data
public class SocialuniImUserDetailRO {
    //是否为加入了黑名单
    Boolean blackUser = false;
    //你是否被对方拉黑了
    Boolean beBlackUser = false;
    //对方是否接收陌生人消息
    Boolean allowStrangerMsg = false;
    // 关闭陌生人消息后将无法给陌生人发送消息，且无法接收陌生人消息
    // 是否是好友
    Boolean friendUser = false;
}
