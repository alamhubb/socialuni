package com.socialuni.social.likee.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeChatDO;
import com.socialuni.social.likee.dao.repository.SocialuniLikeUserChatRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniUserLikeChatManage {
    @Resource
    SocialuniLikeUserChatRepository socialuniLikeUserChatRepository;

    public SocialuniUserLikeChatDO create(Integer chatId) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        SocialuniUserLikeChatDO socialuniUserLikeChatDO = new SocialuniUserLikeChatDO();
        socialuniUserLikeChatDO.setChatId(chatId);
        socialuniUserLikeChatDO.setUserId(mineUserId);
        socialuniUserLikeChatDO = SocialuniRepositoryFacade.save(socialuniUserLikeChatDO);
        return socialuniUserLikeChatDO;
    }

    public SocialuniUserLikeChatDO getOrCreate(Integer chatId) {
        SocialuniUserLikeChatDO socialuniUserLikeChatDO = socialuniLikeUserChatRepository.findByChatId(chatId);
        if (socialuniUserLikeChatDO == null) {
            return create(chatId);
        }
        return socialuniUserLikeChatDO;
    }

    public SocialuniUserLikeChatDO get(Integer chatId) {
        return socialuniLikeUserChatRepository.findByChatId(chatId);
    }
}
