package com.socialuni.social.im.logic.domain;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SocialuniOpenChatDomain {

    @Transactional
    public void openChatByCreateFriend(Integer userId) {

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        //创建 chat new do
        SocialuniChatDO socialuniChatDO =  SocialuniChatDOFactory.getChatIdByCreateSingleChat();


        SocialuniChatUserDO chatUserDO = new SocialuniChatUserDO();

        chatUserDO.setChatId(socialuniChatDO.getId());
        chatUserDO.setUserId(mineUserId);
        chatUserDO.setBeUserId(userId);
        chatUserDO.setType(socialuniChatDO.getType());


        SocialuniChatUserDO chatBeUserDO = new SocialuniChatUserDO();

        chatBeUserDO.setChatId(socialuniChatDO.getId());
        chatBeUserDO.setUserId(userId);
        chatBeUserDO.setBeUserId(mineUserId);
        chatBeUserDO.setType(socialuniChatDO.getType());


        SocialuniRepositoryFacade.save(chatUserDO);
        SocialuniRepositoryFacade.save(chatBeUserDO);

        // 创建 chatUser 设置 user id
    }
}
