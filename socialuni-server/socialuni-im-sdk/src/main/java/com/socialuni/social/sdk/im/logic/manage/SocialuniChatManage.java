package com.socialuni.social.sdk.im.logic.manage;

import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatManage {
    @Resource
    SocialuniChatRepository chatRepository;

    public SocialuniChatDO getOrCreateGroupChat(Integer devId, Long userId, String chatName, String chatType) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByDevIdAndTypeAndChatName(devId, chatType, chatName);
        if (socialuniChatDO == null) {
            socialuniChatDO = SocialuniChatDOFactory.createGroupChatByNameAndType(userId, chatName, chatType);
        }
        return socialuniChatDO;
    }
}
