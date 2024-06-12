package com.socialuni.social.sdk.im.logic.domain;

import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.enumeration.ChatStatus;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.repository.ChatUserRepository;
import com.socialuni.social.sdk.im.enumeration.ChatUserStatus;
import com.socialuni.social.sdk.im.logic.entity.SocialuniChatEntity;
import com.socialuni.social.sdk.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatManage;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Component
@Slf4j
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

    @Resource
    SocialuniUserRepository socialuniUserRepository;

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
        List<Integer> chatUserIds = new ArrayList<>();

        if (UserType.system.equals(user.getType())) {
            List<Integer> ids = socialuniUserRepository.findUserIdsByType(UserType.operation);
            for (Integer id : ids) {
                List<Integer> temChatUserIds = chatUserRepository.findByUserIdAndStatusOrderByUpdateTimeDesc(id, ChatUserStatus.enable);

                chatUserIds.addAll(temChatUserIds);
            }
        }

        List<Integer> mineChatUsers = chatUserRepository.findByUserIdAndStatusOrderByUpdateTimeDesc(user.getUserId(), ChatUserStatus.enable);

        chatUserIds.addAll(mineChatUsers);

        PageRequest pageRequest = PageRequest.of(0, 30);
        chatUserIds = chatUserRepository.queryChatUserIdsByIds(chatUserIds, new Date(), pageRequest);


        List<SocialuniChatUserDO> chatUsers = new ArrayList<>();
        for (Integer temChatUserId : chatUserIds) {
            SocialuniChatUserDO chatSocialuniUserDo = chatUserRepository.findFirstById(temChatUserId);
            chatUsers.add(chatSocialuniUserDo);
        }
        //查询的时候chat列表展示不为当前用户的
        /*return chatUsers.stream().map((ChatUserDO chatUserDO) -> {
            //只有启用的才显示消息列表
            if (chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
                return new ChatVO(chatUserDO.getChat(), chatUserDO, true);
            } else {
                return new ChatVO(chatUserDO.getChat(), chatUserDO);
            }
        }).collect(Collectors.toList());*/
        List<ChatRO> chatROS = SocialChatROFactory.chatUserDOToVOS(chatUsers);
        return chatROS;

    }

    //未登录的情况下查询官方chat，官方群聊
    public List<ChatRO> getNoLoginChats() {
        //未登录的情况只插叙你官方的chats
        List<SocialuniChatDO> chats = chatRepository.findByStatusAndTypeInOrderByTopLevelDescUpdateTimeDesc(ChatStatus.enable, ChatType.systemChats);
        return SocialChatROFactory.chatDOToVOS(chats);
    }
}
