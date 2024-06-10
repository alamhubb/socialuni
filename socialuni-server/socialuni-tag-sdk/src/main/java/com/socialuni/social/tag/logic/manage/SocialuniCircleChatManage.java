package com.socialuni.social.tag.logic.manage;

import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatDOFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCircleChatManage {
    @Resource
    SocialuniChatRepository chatRepository;

    public SocialuniChatDO getOrCreateCircleGroupChat(String chatName, String avatar) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByTypeAndChatName(ChatType.system_group, chatName);
        if (socialuniChatDO == null) {
            socialuniChatDO = SocialuniChatDOFactory.createCircleGroupChat(chatName, avatar);
        }
        return socialuniChatDO;
    }
}
