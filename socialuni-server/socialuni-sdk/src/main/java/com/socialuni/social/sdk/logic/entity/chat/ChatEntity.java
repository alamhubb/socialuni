package com.socialuni.social.sdk.logic.entity.chat;

import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatEntity {
    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private ChatUserRepository chatUserRepository;

    //创建群聊
    public void createGroupChat(SocialuniUserDo groupOwner, List<SocialuniUserDo> groupers) {
        SocialuniChatDO chat = new SocialuniChatDO(ChatType.group);

        //生成chat
        chat = chatRepository.save(chat);

        //match属于私聊，需要保存对方的内容，方便展示头像昵称
        SocialuniChatUserDO groupOwnerChatUser = new SocialuniChatUserDO(chat, groupOwner.getUnionId());

        List<SocialuniChatUserDO> chatUserDOS = new ArrayList<>();
        chatUserDOS.add(groupOwnerChatUser);
        for (SocialuniUserDo grouper : groupers) {
            SocialuniChatUserDO grouperChatUser = new SocialuniChatUserDO(chat, grouper.getUnionId());
            chatUserDOS.add(grouperChatUser);
        }
        chatUserRepository.saveAll(chatUserDOS);
    }
}
