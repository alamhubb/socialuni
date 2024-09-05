package com.socialuni.social.sdk.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.utils.SnowflakeIdUtil;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.enumeration.MessageContentType;
import com.socialuni.social.sdk.im.enumeration.MessageReadStatus;
import com.socialuni.social.sdk.im.enumeration.MessageType;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;

public class SocialuniMessageDOFactory {
    public static SocialuniMessageDO createMessage(Long chatId, String content, Long userId) {
        SocialuniMessageDO messageDO = SocialuniMessageDOFactory.createMessage(chatId, content, userId, MessageContentType.text);
        return messageDO;
    }

    public static SocialuniMessageDO createMessage(Long chatId, String content, Long userId, String contentType) {
        Long messageId = SocialuniUnionIdFacede.createMessageUnionId(SnowflakeIdUtil.nextId());

        SocialuniMessageDO messageDO = new SocialuniMessageDO(userId, messageId, SocialuniContentType.message, content);

        messageDO.setChatId(chatId);
        messageDO.setReadStatus(MessageReadStatus.sending);
        messageDO.setReadNum(0);
        messageDO.setType(MessageType.simple);
        messageDO.setMessageContentType(contentType);

        return messageDO;
    }
}
