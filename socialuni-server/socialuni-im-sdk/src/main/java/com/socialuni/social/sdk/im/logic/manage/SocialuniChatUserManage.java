package com.socialuni.social.sdk.im.logic.manage;

import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.ChatUserRepository;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatUserDOFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserManage {
    @Resource
    ChatUserRepository chatUserRepository;

    public SocialuniChatUserDO joinOrCreateChatUser(SocialuniChatDO chatDO, Integer userId) {
        SocialuniChatUserDO socialuniChatUserDO = chatUserRepository.findOneByChatIdAndUserId(chatDO.getUnionId(), userId);

        if (socialuniChatUserDO == null) {
            socialuniChatUserDO = SocialuniChatUserDOFactory.createGroupChatUser(chatDO, userId);
        }
        return socialuniChatUserDO;
    }

    public SocialuniChatUserDO createUserPersonalChatUser(SocialuniChatDO chatDO, Integer userId) {

        SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOFactory.createUserPersonalChatUser(chatDO, userId);
        return socialuniChatUserDO;
    }
}
