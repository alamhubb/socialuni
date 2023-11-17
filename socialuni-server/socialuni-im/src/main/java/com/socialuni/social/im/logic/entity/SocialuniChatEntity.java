package com.socialuni.social.im.logic.entity;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.enumeration.SocialuniChatOperateType;
import com.socialuni.social.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.im.logic.manage.SocialuniChatManage;
import com.socialuni.social.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.user.sdk.factory.SocialuniAppOperateRecordDOFactory;
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


    public SocialuniChatDO getOrCreateSystemChatUser(String chatName, SocialuniUserDo user) {
        SocialuniChatDO socialuniChatDO = socialuniChatManage.getOrCreateSystemGroupChat(chatName);
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.getOrCreateChat(socialuniChatDO, user.getUserId());
        return socialuniChatDO;
    }


    //不管官方群聊，我们就创建用户的群聊
    public SocialuniChatDO getOrCreateUserPersonalChat(SocialuniUserDo socialuniUserDo) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByTypeAndUserIdOrderByCreateTimeDesc(ChatType.userPersonalGroup, socialuniUserDo.getUserId());
        if (socialuniChatDO == null) {
            socialuniChatDO = this.createUserPersonalChat(socialuniUserDo);
        }
        return socialuniChatDO;
    }

    private SocialuniChatDO createUserPersonalChat(SocialuniUserDo socialuniUserDo) {
        SocialuniChatDO socialuniChatDO = SocialuniChatDOFactory.createUserPersonalChat(socialuniUserDo);
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.createUserPersonalChatUser(socialuniChatDO, socialuniUserDo.getUserId());

        SocialuniAppOperateRecordDOFactory.createChatAppOperateRecordDOBySys(
                SocialuniContentType.chat,
                socialuniChatDO.getUnionId(),
                SocialuniChatOperateType.createGroupChat,
                socialuniUserDo.getUserId(),
                "创建用户个人群组"
        );
        return socialuniChatDO;
    }
}
