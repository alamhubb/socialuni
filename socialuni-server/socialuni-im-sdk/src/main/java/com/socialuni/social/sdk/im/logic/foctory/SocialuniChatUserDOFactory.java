package com.socialuni.social.sdk.im.logic.foctory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;

public class SocialuniChatUserDOFactory {


    public static List<SocialuniChatUserDO> getOrCreateChatUsersBySingleSendMsg(Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(userId, beUserId);
        SocialuniChatUserDO beChatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(beUserId, userId);
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

    public static List<SocialuniChatUserDO> createSocialuniChatUserDOS(Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO;
        SocialuniChatUserDO beChatUserDO;
        //会话不存在则创建
        SocialuniChatDO chatDO = SocialuniChatDOFactory.getChatIdByCreateByDomainType();
        //会话不存在则创建
        chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);
        chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);

        //存在自己给自己发消息的情况
        if (!userId.equals(beUserId)) {
            beChatUserDO = new SocialuniChatUserDO(chatDO, beUserId, userId);
            beChatUserDO = SocialuniRepositoryFacade.save(beChatUserDO);
            return Arrays.asList(chatUserDO, beChatUserDO);
        }
        return Arrays.asList(chatUserDO, chatUserDO);
    }

    public static SocialuniChatUserDO getOrCreateChatUserBySingleReceiveMsg(SocialuniChatDO chatDO, Long userId, Long beUserId) {
        SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(userId, beUserId);
        if (chatUserDO == null) {
            //会话不存在则创建
            chatUserDO = new SocialuniChatUserDO(chatDO, userId, beUserId);

            chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);
        }
        return chatUserDO;
    }

    public static SocialuniChatUserDO createGroupChatUser(SocialuniChatDO chatDO, Long userId) {
        if (ObjectUtils.isEmpty(userId)){
            throw new SocialParamsException("获取会话异常2034301");
        }
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId);

        if (chatUserDO.getType().equals(ChatType.system_group)) {
            Long systemUserId = SocialuniUserUtil.getSystemUserIdNotNull();
            if (userId.equals(systemUserId)) {
                chatUserDO.setChatRoleId(SocialuniSysRoleId.owner);
            }
        }

        chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);
        return chatUserDO;
    }

    public static SocialuniChatUserDO createUserPersonalChatUser(SocialuniChatDO chatDO, Long userId) {
        //会话不存在则创建
        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO(chatDO, userId);

        chatUserDO.setChatRoleId(SocialuniSysRoleId.owner);

        chatUserDO = SocialuniChatUserDOUtil.save(chatUserDO);
        return chatUserDO;
    }
}
