package com.socialuni.social.sdk.service;

import com.socialuni.entity.model.DO.chat.ChatDO;
import com.socialuni.entity.model.DO.chat.ChatUserDO;
import lombok.Data;

@Data
public class CreateSingleChatResult {
    private ChatDO chat;
    private ChatUserDO mineChatUser;
    private ChatUserDO receiveChatUser;

    public CreateSingleChatResult(ChatDO chat, ChatUserDO mineChatUser, ChatUserDO receiveChatUser) {
        this.chat = chat;
        this.mineChatUser = mineChatUser;
        this.receiveChatUser = receiveChatUser;
    }
}
