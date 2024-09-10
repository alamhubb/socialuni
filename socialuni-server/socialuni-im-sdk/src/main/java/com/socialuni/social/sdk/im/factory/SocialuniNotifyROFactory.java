package com.socialuni.social.sdk.im.factory;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.sdk.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.sdk.im.notify.NotifyVO;
import com.socialuni.social.common.api.model.SocialuniNotifyRO;

import java.util.List;

public class SocialuniNotifyROFactory {

    public static NotifyVO getNotifyROBySendMsg(String notifyType, SocialuniUserDo sendUser, SocialuniMessageDO socialuniMessageDO, SocialuniChatDO chatDO) {
        NotifyVO notifyVO = new NotifyVO(sendUser);

        ChatRO chat = SocialChatROFactory.getChatROByQueryChat(chatDO, socialuniMessageDO);

        notifyVO.setData(chat);
        notifyVO.setType(notifyType);

        return notifyVO;
    }

    public static SocialuniNotifyRO getNotifyGroupMessageRO(ChatRO data, List<String> userIds) {
        return getNotifyRO(NotifyType.message, data, userIds);
    }

    public static SocialuniNotifyRO getNotifyRO(String type, Object data, List<String> userIds) {
        SocialuniNotifyRO socialuniNotifyRO = SocialuniNotifyRO.builder()
//                .user(socialuniUserRO)
                .type(type)
                .data(data)
                .userIds(userIds)
                .build();
        return socialuniNotifyRO;
    }

}
