package com.socialuni.social.im.api.model.RO;

import lombok.Data;

@Data
public class SocialuniImMineUserDetailRO {
    //对方是否接收陌生人消息
    // 关闭陌生人消息后将无法给陌生人发送消息，且无法接收陌生人消息
    Boolean allowStrangerMsg = null;


}
