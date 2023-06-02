package com.socialuni.social.im.logic.manage;

import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.im.logic.foctory.SocialuniChatUserDOFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserManage {
    @Resource
    ChatUserRepository chatUserRepository;

    public SocialuniChatUserDO getOrCreateChat(SocialuniChatDO chatDO, Integer userId) {
        SocialuniChatUserDO socialuniChatUserDO = chatUserRepository.findOneByChatIdAndUserId(chatDO.getUnionId(), userId);

        if (socialuniChatUserDO == null) {
            socialuniChatUserDO = SocialuniChatUserDOFactory.createGroupChatUser(chatDO, userId);
        }
        return socialuniChatUserDO;
    }
}
