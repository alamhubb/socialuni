package com.socialuni.social.im.logic.domain;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

@Service
public class OpenChatDomain {

    public void openChatByAgreeFriend(Integer userId) {

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

        chatUserDO.setChatId(socialuniChatDO.getId());
        chatUserDO.setUserId(userId);
        chatUserDO.setBeUserId(mineUserId);
        chatUserDO.setType(socialuniChatDO.getType());


        SocialuniRepositoryFacade.save(chatBeUserDO);
        SocialuniRepositoryFacade.save(chatBeUserDO);


        // 创建 chatUser 设置 user id
    }
}
