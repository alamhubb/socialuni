package com.socialuni.social.likee.model;

import com.socialuni.social.im.api.model.RO.ChatRO;
import lombok.Data;

@Data
public class SocialuniLikeChatRO extends ChatRO {
    Integer sendMsgNeedCoin;
    //会话是否需要付费开启
    boolean needPayOpen;
}
