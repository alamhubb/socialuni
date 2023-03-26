package com.socialuni.social.im.logic.service.chat;

import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import lombok.Data;

@Data
public class CreateSingleChatResult {
    private SocialuniChatDO chat;
    private ChatUserDO mineChatUser;
    private ChatUserDO receiveChatUser;

    public CreateSingleChatResult(SocialuniChatDO chat, ChatUserDO mineChatUser, ChatUserDO receiveChatUser) {
        this.chat = chat;
        this.mineChatUser = mineChatUser;
        this.receiveChatUser = receiveChatUser;
    }
}
