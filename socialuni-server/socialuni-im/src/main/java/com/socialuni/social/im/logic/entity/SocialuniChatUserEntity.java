package com.socialuni.social.im.logic.entity;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.enumeration.ChatUserStatus;
import com.socialuni.social.im.logic.manage.SocialuniChatDOFactory;

public class SocialuniChatUserEntity {

    public static ChatUserDO createChatUserBySingle(Integer userId, Integer beUserId) {
        Integer chatId = SocialuniChatDOFactory.getChatIdByCreateSingleChat();
        //会话不存在则创建
        ChatUserDO chatUserDO = new ChatUserDO(chatId, userId, beUserId);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);

        return chatUserDO;
    }

    public static ChatUserDO createChatUserBySingleSendMsg(Integer userId, Integer beUserId) {
        Integer chatId = SocialuniChatDOFactory.getChatIdByCreateSingleChat();
        //会话不存在则创建
        ChatUserDO chatUserDO = new ChatUserDO(chatId, userId, beUserId);
        chatUserDO.setStatus(ChatUserStatus.enable);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);

        return chatUserDO;
    }
}
