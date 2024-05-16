package com.socialuni.social.sdk.im.logic.foctory;

import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import java.util.Arrays;
import java.util.List;

public class SocialuniChatUserDOFactory {

    public static SocialuniChatUserDO getChatUserDO(Integer userId, Integer beUserId) {
        return SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, SocialuniChatUserDO.class);
    }

    public static List<SocialuniChatUserDO> getOrCreateChatUsersBySingleSendMsg(Integer userId, Integer beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, SocialuniChatUserDO.class);
        SocialuniChatUserDO beChatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(beUserId, userId, SocialuniChatUserDO.class);
        if (chatUserDO == null) {
            if (beChatUserDO != null) {
                throw new SocialSystemException("异常逻辑，非正常存在的会话");
            }
            return createSocialuniChatUserDOS(userId, beUserId);
        }
        if (!userId.equals(beUserId)) {
            return Arrays.asList(chatUserDO, beChatUserDO);
        }
        return Arrays.asList(chatUserDO);
    }

    public static List<SocialuniChatUserDO> createSocialuniChatUserDOS(Integer userId, Integer beUserId) {
        SocialuniChatUserDO chatUserDO;
        SocialuniChatUserDO beChatUserDO;
        //会话不存在则创建
        SocialuniChatDO chatDO = SocialuniChatDOFactory.getChatIdByCreateByType();
        //会话不存在则创建
        chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);
        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);

        //存在自己给自己发消息的情况
        if (!userId.equals(beUserId)) {
            beChatUserDO = new SocialuniChatUserDO(chatDO, beUserId, userId);
            beChatUserDO = SocialuniRepositoryFacade.save(beChatUserDO);
            return Arrays.asList(chatUserDO, beChatUserDO);
        }
        return Arrays.asList(chatUserDO, chatUserDO);
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

    public static SocialuniChatUserDO createGroupChatUser(SocialuniChatDO chatDO, Integer userId) {
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId);

        if (chatUserDO.getType().equals(ChatType.system_group)) {
            Integer systemUserId = SocialuniUserUtil.getSystemUserIdNotNull();
            if (userId.equals(systemUserId)) {
                chatUserDO.setChatRoleId(SocialuniSysRoleId.owner);
            }
        }

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        return chatUserDO;
    }

    public static SocialuniChatUserDO createUserPersonalChatUser(SocialuniChatDO chatDO, Integer userId) {
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId);

        chatUserDO.setChatRoleId(SocialuniSysRoleId.owner);

        chatUserDO = SocialuniRepositoryFacade.save(chatUserDO);
        return chatUserDO;
    }
}
