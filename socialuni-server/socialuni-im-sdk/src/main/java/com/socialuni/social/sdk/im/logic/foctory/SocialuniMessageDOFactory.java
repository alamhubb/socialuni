package com.socialuni.social.sdk.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.enumeration.MessageContentType;
import com.socialuni.social.sdk.im.enumeration.MessageReadStatus;
import com.socialuni.social.sdk.im.enumeration.MessageType;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;

public class SocialuniMessageDOFactory {

    public static SocialuniMessageDO createMessage(Integer chatId, String content, Integer userId) {
        Integer messageId = SocialuniUnionIdFacede.createMessageUnionId();

        SocialuniMessageDO messageDO = new SocialuniMessageDO(userId, messageId, SocialuniContentType.message, content);

        messageDO.setChatId(chatId);
        messageDO.setReadStatus(MessageReadStatus.sending);
        messageDO.setReadNum(0);
        messageDO.setType(MessageType.simple);
        messageDO.setMessageContentType(MessageContentType.text);

        return messageDO;
    }

    public static SocialuniMessageDO createMessage(Integer chatId, String content, Integer userId, String messageType) {
        SocialuniMessageDO messageDO = SocialuniMessageDOFactory.createMessage(chatId, content, userId);
        messageDO.setType(messageType);

        return messageDO;
    }
}
