package com.socialuni.social.im.logic.domain;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SocialuniOpenChatDomain {

    @Transactional
    public void openChatByCreateFriend(Integer userId) {

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        //创建 chat new do
        SocialuniChatDO socialuniChatDO = new SocialuniChatDO(ChatType.single);

        socialuniChatDO = SocialuniRepositoryFacade.save(socialuniChatDO);

        ChatUserDO chatUserDO = new ChatUserDO();

        chatUserDO.setChatId(socialuniChatDO.getId());
        chatUserDO.setUserId(mineUserId);
        chatUserDO.setBeUserId(userId);
        chatUserDO.setType(socialuniChatDO.getType());


        ChatUserDO chatBeUserDO = new ChatUserDO();

        chatBeUserDO.setChatId(socialuniChatDO.getId());
        chatBeUserDO.setUserId(userId);
        chatBeUserDO.setBeUserId(mineUserId);
        chatBeUserDO.setType(socialuniChatDO.getType());


        SocialuniRepositoryFacade.save(chatUserDO);
        SocialuniRepositoryFacade.save(chatBeUserDO);

        // 创建 chatUser 设置 user id
    }
}
