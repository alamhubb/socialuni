package com.socialuni.sdk.logic.entity.chat;

import com.socialuni.sdk.dao.repository.ChatRepository;
import com.socialuni.sdk.dao.DO.chat.ChatDO;
import com.socialuni.sdk.dao.DO.chat.ChatUserDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.constant.socialuni.ChatType;
import com.socialuni.sdk.dao.repository.ChatUserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatEntity {
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private ChatUserRepository chatUserRepository;

    //创建群聊
    public void createGroupChat(SocialuniUserDO groupOwner, List<SocialuniUserDO> groupers) {
        ChatDO chat = new ChatDO(ChatType.group);

        //生成chat
        chat = chatRepository.save(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        ChatUserDO groupOwnerChatUser = new ChatUserDO(chat, groupOwner.getUnionId());

        List<ChatUserDO> chatUserDOS = new ArrayList<>();
        chatUserDOS.add(groupOwnerChatUser);
        for (SocialuniUserDO grouper : groupers) {
            ChatUserDO grouperChatUser = new ChatUserDO(chat, grouper.getUnionId());
            chatUserDOS.add(grouperChatUser);
        }
        chatUserRepository.saveAll(chatUserDOS);
    }
}
