package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;

import java.util.Arrays;
import java.util.List;

public class SocialuniChatUserDOFactory {

    public static SocialuniChatUserDO createChatUserBySingle(Integer userId, Integer beUserId) {
//        Integer chatId = SocialuniChatDOFactory.getChatIdByCreateSingleChat();
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO();
//        ChatUserDO chatUserDO = new ChatUserDO(0, userId, beUserId);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);

        return chatUserDO;
    }

    public static List<SocialuniChatUserDO> getOrCreateChatUsersBySingleSendMsg(Integer userId, Integer beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, SocialuniChatUserDO.class);
        SocialuniChatUserDO beChatUserDO = null;

        if (chatUserDO == null) {
            beChatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(beUserId, userId, SocialuniChatUserDO.class);
            if (beChatUserDO != null) {
                throw new SocialSystemException("异常逻辑，非正常存在的会话");
            }

            //会话不存在则创建
            SocialuniChatDO chatDO = SocialuniChatDOFactory.getChatIdByCreateSingleChat();
            //会话不存在则创建
            chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);
            beChatUserDO = new SocialuniChatUserDO(chatDO, beUserId, userId);

            chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
            beChatUserDO = SocialuniRepositoryFacade.save(beChatUserDO);
        }
        return Arrays.asList(chatUserDO, beChatUserDO);
    }

    public static SocialuniChatUserDO createChatUserByCreateFriend(SocialuniChatDO chatDO, Integer userId, Integer beUserId) {
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        return chatUserDO;
    }


    public static SocialuniChatUserDO getOrCreateChatUserBySingleReceiveMsg(SocialuniChatDO chatDO, Integer userId, Integer beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, SocialuniChatUserDO.class);
        if (chatUserDO == null) {
            //会话不存在则创建
            chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);

            chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        }
        return chatUserDO;
    }
}
