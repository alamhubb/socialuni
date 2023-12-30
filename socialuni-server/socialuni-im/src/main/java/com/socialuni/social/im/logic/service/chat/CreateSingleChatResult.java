package com.socialuni.social.im.logic.service.chat;

import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import lombok.Data;

@Data
public class CreateSingleChatResult {
    private SocialuniChatDO chat;
    private SocialuniChatUserDO mineChatUser;
    private SocialuniChatUserDO receiveChatUser;

    public CreateSingleChatResult(SocialuniChatDO chat, SocialuniChatUserDO mineChatUser, SocialuniChatUserDO receiveChatUser) {
        this.chat = chat;
        this.mineChatUser = mineChatUser;
        this.receiveChatUser = receiveChatUser;
    }
}
