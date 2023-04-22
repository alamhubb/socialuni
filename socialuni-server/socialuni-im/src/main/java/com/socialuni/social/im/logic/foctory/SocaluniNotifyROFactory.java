package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.im.model.message.notify.NotifyVO;

public class SocaluniNotifyROFactory {

    public static NotifyVO getNotifyROBySendMsg(NotifyDO notify, SocialuniUserDo sendUser, SocialuniMessageReceiveDO messageReceive, SocialuniChatUserDO chatUser){
        NotifyVO notifyVO  = new NotifyVO(sendUser);

        ChatRO chat = SocialChatROFactory.getChatROBySendMsg(chatUser, messageReceive);

        notifyVO.setChat(chat);
        notifyVO.setType(notify.getType());

        return notifyVO;
    }

}
