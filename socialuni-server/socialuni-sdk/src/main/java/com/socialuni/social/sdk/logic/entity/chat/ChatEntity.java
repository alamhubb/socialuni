package com.socialuni.social.sdk.logic.entity.chat;

import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.repository.ChatRepository;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    public void createGroupChat(SocialuniUserDo groupOwner, List<SocialuniUserDo> groupers) {
        SocialuniChatDO chat = new SocialuniChatDO(ChatType.group);

        //生成chat
        chat = chatRepository.save(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        ChatUserDO groupOwnerChatUser = new ChatUserDO(chat, groupOwner.getUnionId());

        List<ChatUserDO> chatUserDOS = new ArrayList<>();
        chatUserDOS.add(groupOwnerChatUser);
        for (SocialuniUserDo grouper : groupers) {
            ChatUserDO grouperChatUser = new ChatUserDO(chat, grouper.getUnionId());
            chatUserDOS.add(grouperChatUser);
        }
        chatUserRepository.saveAll(chatUserDOS);
    }
}
