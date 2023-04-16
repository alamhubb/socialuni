package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.enumeration.ChatType;

public class SocialuniChatUserDOFactory {

    public static ChatUserDO createChatUserBySingle(Integer userId, Integer beUserId) {
//        Integer chatId = SocialuniChatDOFactory.getChatIdByCreateSingleChat();
        //会话不存在则创建
        ChatUserDO chatUserDO = new ChatUserDO();
//        ChatUserDO chatUserDO = new ChatUserDO(0, userId, beUserId);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);

        return chatUserDO;
    }

    public static ChatUserDO getOrCreateChatUserBySingleSendMsg(Integer userId, Integer beUserId) {
        ChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, ChatUserDO.class);
        if (chatUserDO == null) {
            //会话不存在则创建
            SocialuniChatDO chatDO = SocialuniChatDOFactory.getChatIdByCreateSingleChat();
            //会话不存在则创建
            chatUserDO = new ChatUserDO(chatDO, userId, beUserId);

            chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        }
        return chatUserDO;
    }

    public static ChatUserDO createChatUserByCreateFriend(SocialuniChatDO chatDO, Integer userId, Integer beUserId) {
        //会话不存在则创建
        ChatUserDO chatUserDO = new ChatUserDO(chatDO, userId, beUserId);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        return chatUserDO;
    }


    public static ChatUserDO getOrCreateChatUserBySingleReceiveMsg(SocialuniChatDO chatDO, Integer userId, Integer beUserId) {
        ChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, ChatUserDO.class);
        if (chatUserDO == null) {
            //会话不存在则创建
            chatUserDO = new ChatUserDO(chatDO, userId, beUserId);

            chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        }
        return chatUserDO;
    }
}
