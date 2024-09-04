package com.socialuni.social.sdk.im.logic.manage;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.enumeration.ChatUserStatus;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatUserDOFactory;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class SocialuniChatUserManage {
    @Resource
    SocialuniChatUserRepository chatUserRepository;

    public SocialuniChatUserDO joinOrCreateChatUser(SocialuniChatDO chatDO, Long userId) {
        SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(chatDO.getUnionId(), userId);

        if (socialuniChatUserDO == null) {
            socialuniChatUserDO = SocialuniChatUserDOFactory.createGroupChatUser(chatDO, userId);
        }
        return socialuniChatUserDO;
    }

    public SocialuniChatUserDO createUserPersonalChatUser(SocialuniChatDO chatDO, Long userId) {

        SocialuniChatUserDO socialuniChatUserDO = SocialuniChatUserDOFactory.createUserPersonalChatUser(chatDO, userId);
        return socialuniChatUserDO;
    }

    public SocialuniChatUserDO getMyChatUser(Integer chatId, Integer beUserId) {
        List<SocialuniChatUserDO> list = chatUserRepository.findByChatIdAndStatus(chatId, ChatUserStatus.enable);
        Optional<SocialuniChatUserDO> socialuniChatUserDO = list.stream().filter(item -> item.getBeUserId().equals(beUserId)).findFirst();
        SocialuniChatUserDO socialuniChatUserDO1 = socialuniChatUserDO.orElseThrow(() -> new SocialParamsException("不存在会话信息"));
        return socialuniChatUserDO1;
    }
}
