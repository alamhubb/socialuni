package com.socialuni.social.sdk.im.logic.entity;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.api.feign.SocialuniChatAPI;
import com.socialuni.social.im.api.model.QO.SocialuniChatCreateQO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.enumeration.ChatStatus;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.enumeration.SocialuniChatOperateType;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatManage;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.factory.SocialuniAppOperateRecordDOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniChatEntity {
    @Resource
    SocialuniChatManage socialuniChatManage;
    @Resource
    SocialuniChatUserManage socialuniChatUserManage;
    @Resource
    SocialuniChatRepository chatRepository;


    public SocialuniChatDO getJoinOrCreateChatUser(Long createUserId, String chatType, Long joinUserId, String chatName) {
        SocialuniChatDO socialuniChatDO = socialuniChatManage.getOrCreateGroupChat(createUserId, chatName, chatType);
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.joinOrCreateChatUser(socialuniChatDO, joinUserId);
        return socialuniChatDO;
    }


    public SocialuniChatDO getJoinOrCreateChatUser(Long chatId, Long userId) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByUnionId(chatId);
        if (socialuniChatDO == null) {
            throw new SocialBusinessException("不存在的会话");
        }
        if (!socialuniChatDO.getStatus().equals(ChatStatus.enable)) {
            throw new SocialBusinessException("不存在的会话");
        }
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.joinOrCreateChatUser(socialuniChatDO, userId);
        return socialuniChatDO;
    }

    @Resource
    SocialuniChatAPI socialuniChatAPI;


    public void createUserChats(SocialuniUserDo user) {
        SocialuniChatDO ownerChat = this.getOrCreateUserPersonalChat(user);


        //判断用户是否加入了默认的聊天群

        List<String> groups = SocialuniAppConfig.getAppConfig().getDefaultChatGroups();

        //什么时候创建这个默认群呢
        //启动的时候
        //修改的时候

        //需要将用户加入到这些群聊中
        for (String group : groups) {
            Long sysUserId = DevAccountFacade.getDevUserId();

            SocialuniChatDO socialuniChatDO = this.getJoinOrCreateChatUser(sysUserId, ChatType.system_group, user.getUserId(), group);

            SocialuniChatCreateQO socialuniChatCreateQO = new SocialuniChatCreateQO();
            socialuniChatCreateQO.setChatName(socialuniChatDO.getChatName());
            socialuniChatCreateQO.setType(socialuniChatDO.getType());

            ResultRO<ChatRO> resultRO = socialuniChatAPI.joinOrCreateGroupChat(socialuniChatCreateQO);
            ChatRO chatRO = resultRO.getData();

            SocialuniUnionIdFacede.updateUuidByUnionIdNotNull(socialuniChatDO.getUnionId(), chatRO.getId());
        }
    }


    //不管官方群聊，我们就创建用户的群聊
    public SocialuniChatDO getOrCreateUserPersonalChat(SocialuniUserDo socialuniUserDo) {
        SocialuniChatDO socialuniChatDO = chatRepository.findFirstByTypeAndUserId(ChatType.userPersonalGroup, socialuniUserDo.getUserId());
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
