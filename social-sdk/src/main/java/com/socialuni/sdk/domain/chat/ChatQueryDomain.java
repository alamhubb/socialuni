package com.socialuni.sdk.domain.chat;

import com.socialuni.constant.ChatType;
import com.socialuni.constant.ChatStatus;
import com.socialuni.sdk.factory.SocialChatROFactory;
import com.socialuni.entity.model.DO.chat.ChatDO;
import com.socialuni.entity.model.DO.chat.ChatUserDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.repository.ChatRepository;
import com.socialuni.sdk.repository.ChatUserRepository;
import com.socialuni.social.model.model.RO.message.chat.ChatRO;
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
    ChatRepository chatRepository;
    @Resource
    ChatUserRepository chatUserRepository;

    //登录情况下查询用户有权限的chatuser
    //初始化和查询chat列表触发的
    public List<ChatRO> getChats(UserDO user) {
        //未登录的情况只插叙你官方的chats
        List<ChatUserDO> chatUsers = chatUserRepository.findByChatStatusAndUserIdAndFrontShowTrueOrderByChatTopLevelDescTopFlagDescUpdateTimeDesc(ChatStatus.enable, user.getId());
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
    public List<ChatRO> getChats() {
        //未登录的情况只插叙你官方的chats
        List<ChatDO> chats = chatRepository.findByStatusAndTypeInOrderByTopLevelDescUpdateTimeDesc(ChatStatus.enable, ChatType.systemChats);
        return SocialChatROFactory.chatDOToVOS(chats);
    }
}
