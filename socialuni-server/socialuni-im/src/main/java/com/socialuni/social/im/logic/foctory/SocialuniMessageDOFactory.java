package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.im.dao.DO.message.MessageDO;
import com.socialuni.social.im.enumeration.MessageContentType;
import com.socialuni.social.im.enumeration.MessageReadStatus;
import com.socialuni.social.im.enumeration.MessageType;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;

public class SocialuniMessageDOFactory {

    public static MessageDO createMessage(Integer chatId, String content, Integer userId) {
        MessageDO messageDO = new MessageDO(userId, SocialuniContentType.message, content);

        Integer messageId = SocialuniUnionIdFacede.createMessageUnionId();

        messageDO.setUnionId(messageId);
        messageDO.setChatId(chatId);
        messageDO.setReadStatus(MessageReadStatus.sending);
        messageDO.setReadNum(0);
        messageDO.setType(MessageType.simple);
        messageDO.setMessageContentType(MessageContentType.text);

        return messageDO;
    }

    public static MessageDO createMessage(Integer chatId, String content, Integer userId, String messageType) {
        MessageDO messageDO = SocialuniMessageDOFactory.createMessage(userId, content, userId);
        messageDO.setType(messageType);

        return messageDO;
    }
}
