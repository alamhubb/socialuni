package com.socialuni.sdk.service;

import com.socialuni.sdk.model.DO.chat.ChatDO;
import com.socialuni.sdk.model.DO.chat.ChatUserDO;
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
