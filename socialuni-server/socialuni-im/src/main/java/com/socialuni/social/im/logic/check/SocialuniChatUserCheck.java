package com.socialuni.social.im.logic.check;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.im.enumeration.ChatUserStatus;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import javax.annotation.Resource;

public class SocialuniChatUserCheck {
    @Resource
    private ChatUserRepository chatUserRepository;

    public SocialuniChatUserDO CheckUserInChat(Integer chatId,Integer sendUserId) {
        SocialuniChatDO chat = SocialuniRepositoryFacade.findByUnionId(chatId, SocialuniChatDO.class);

        if (chat == null) {
            throw new SocialParamsException("不存在的群聊");
        }

        SocialuniChatUserDO chatUserDO = chatUserRepository.findFirstByChatIdAndUserIdAndStatus(chatId, sendUserId, ChatUserStatus.enable);

        if (chatUserDO == null) {
            throw new SocialParamsException("未加入群聊");
        }
        return chatUserDO;
    }
}
