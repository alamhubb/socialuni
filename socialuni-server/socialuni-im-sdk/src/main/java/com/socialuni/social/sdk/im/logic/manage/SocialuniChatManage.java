package com.socialuni.social.sdk.im.logic.manage;

import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatDOFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatManage {
    @Resource
    SocialuniChatRepository chatRepository;

    public SocialuniChatDO getOrCreateSystemGroupChat(String chatName) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByTypeAndChatName(ChatType.system_group, chatName);
        if (socialuniChatDO == null) {
            socialuniChatDO = SocialuniChatDOFactory.createSystemGroupChat(chatName);
        }
        return socialuniChatDO;
    }
}
