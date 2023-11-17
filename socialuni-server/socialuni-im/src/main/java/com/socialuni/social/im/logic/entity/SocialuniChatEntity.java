package com.socialuni.social.im.logic.entity;

import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
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

    public SocialuniChatDO createGroupChat(String groupName, Integer userId){
        SocialuniChatDO socialuniChatDO = socialuniChatManage.getOrCreateChat(groupName);
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.getOrCreateChat(socialuniChatDO, userId);


    }
}
