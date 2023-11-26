package com.socialuni.social.im.logic.domain;

import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.enumeration.ChatStatus;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.im.enumeration.ChatUserStatus;
import com.socialuni.social.im.logic.entity.SocialuniChatEntity;
import com.socialuni.social.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.logic.manage.SocialuniChatManage;
import com.socialuni.social.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Component
public class ChatQueryDomain {
    @Resource
    SocialuniChatRepository chatRepository;
    @Resource
    ChatUserRepository chatUserRepository;
    @Resource
    SocialuniChatManage socialuniChatManage;
    @Resource
    SocialuniChatUserManage socialuniChatUserManage;
    @Resource
    SocialuniChatEntity socialuniChatEntity;


    public List<ChatRO> getChats() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUser == null) {
            return this.getNoLoginChats();
        } else {
            return this.getUserChats(mineUser);
        }
    }

    //登录情况下查询用户有权限的chatuser
    //初始化和查询chat列表触发的
    public List<ChatRO> getUserChats(SocialuniUserDo user) {

        socialuniChatEntity.createUserChats(user);


        //未登录的情况只插叙你官方的chats
        List<SocialuniChatUserDO> chatUsers = chatUserRepository.findByStatusAndUserIdOrderByUpdateTimeDesc(ChatUserStatus.enable, user.getUserId());
        //查询的时候chat列表展示不为当前用户的
        /*return chatUsers.stream().map((ChatUserDO chatUserDO) -> {
            //只有启用的才显示消息列表
            if (chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
                return new ChatVO(chatUserDO.getChat(), chatUserDO, true);
            } else {
                return new ChatVO(chatUserDO.getChat(), chatUserDO);
            }
        }).collect(Collectors.toList());*/
        return SocialChatROFactory.chatUserDOToVOS(chatUsers);
    }

    //未登录的情况下查询官方chat，官方群聊
    public List<ChatRO> getNoLoginChats() {
        //未登录的情况只插叙你官方的chats
        List<SocialuniChatDO> chats = chatRepository.findByStatusAndTypeInOrderByTopLevelDescUpdateTimeDesc(ChatStatus.enable, ChatType.systemChats);
        return SocialChatROFactory.chatDOToVOS(chats);
    }
}
