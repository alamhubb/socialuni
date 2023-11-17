package com.socialuni.social.im.logic.manage;

import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.logic.foctory.SocialuniChatDOFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatManage {
    @Resource
    SocialuniChatRepository chatRepository;

    public SocialuniChatDO getOrCreateChat(String chatName) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByTypeAndChatName(ChatType.system_group, chatName);
        if (socialuniChatDO == null) {
            socialuniChatDO = this.createChat(chatName);
        }
        return socialuniChatDO;
    }

    public SocialuniChatDO createChat(String chatName) {
        SocialuniChatDO socialuniChatDO = SocialuniChatDOFactory.createGroupChat(chatName);
        return socialuniChatDO;
    }
}
