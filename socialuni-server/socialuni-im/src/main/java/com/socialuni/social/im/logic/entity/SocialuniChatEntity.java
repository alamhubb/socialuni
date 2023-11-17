package com.socialuni.social.im.logic.entity;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.im.logic.manage.SocialuniChatManage;
import com.socialuni.social.im.logic.manage.SocialuniChatUserManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniChatEntity {
    @Resource
    SocialuniChatManage socialuniChatManage;
    @Resource
    SocialuniChatUserManage socialuniChatUserManage;
    @Resource
    SocialuniChatRepository chatRepository;

    //不管官方群聊，我们就创建用户的群聊

    public SocialuniChatDO getOrCreateUserRoomChat(SocialuniUserDo socialuniUserDo) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByTypeAndUserId(ChatType.user_room_group, socialuniUserDo.getUserId());
        if (socialuniChatDO == null) {
            socialuniChatDO = SocialuniChatDOFactory.createUserRoomGroupChat(socialuniUserDo);

            SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.getOrCreateChat(socialuniChatDO, userId);
        }


    }

    public SocialuniChatDO createUserRoomChat(SocialuniUserDo socialuniUserDo) {
        SocialuniChatDO socialuniChatDO = SocialuniChatDOFactory.createUserRoomGroupChat(socialuniUserDo);

        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.getOrCreateChat(socialuniChatDO, userId);


    }
}
