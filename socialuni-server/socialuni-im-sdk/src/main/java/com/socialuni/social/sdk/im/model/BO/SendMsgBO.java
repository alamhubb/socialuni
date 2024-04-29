package com.socialuni.social.sdk.im.model.BO;

import lombok.Data;

@Data
public class SendMsgBO {

    Integer receiveUserId;
    String msgContent;
    String type;
}
