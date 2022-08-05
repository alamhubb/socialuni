package com.socialuni.center.web.entity.chat;

import com.socialuni.center.web.repository.ChatRepository;
import com.socialuni.social.constant.ChatType;
import com.socialuni.social.entity.model.DO.chat.ChatDO;
import com.socialuni.social.entity.model.DO.chat.ChatUserDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.repository.ChatUserRepository;
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
    public void createGroupChat(UserDO groupOwner, List<UserDO> groupers) {
        ChatDO chat = new ChatDO(ChatType.group);

        //生成chat
        chat = chatRepository.save(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        ChatUserDO groupOwnerChatUser = new ChatUserDO(chat, groupOwner.getId());

        List<ChatUserDO> chatUserDOS = new ArrayList<>();
        chatUserDOS.add(groupOwnerChatUser);
        for (UserDO grouper : groupers) {
            ChatUserDO grouperChatUser = new ChatUserDO(chat, grouper.getId());
            chatUserDOS.add(grouperChatUser);
        }
        chatUserRepository.saveAll(chatUserDOS);
    }
}
