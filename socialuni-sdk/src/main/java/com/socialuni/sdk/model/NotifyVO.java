package com.socialuni.sdk.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.sdk.constant.NotifyType;
import com.socialuni.sdk.factory.SocialChatROFactory;
import com.socialuni.sdk.model.DO.NotifyDO;
import com.socialuni.sdk.model.DO.chat.ChatDO;
import com.socialuni.sdk.model.DO.chat.ChatUserDO;
import com.socialuni.sdk.model.DO.message.MessageDO;
import com.socialuni.sdk.model.DO.message.MessageReceiveDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.message.chat.ChatRO;
import com.socialuni.social.web.sdk.utils.JsonUtil;
import lombok.Data;
import org.springframework.web.socket.TextMessage;

/**
 * @author qinkaiyuan
 * @date 2019-06-12 22:21
 */
@Data
public class NotifyVO {
    //加入一列作为外键
    private ChatRO chat;

    private NotifyUserVO user;
    private Integer receiveUserId;

    private String type;

    public NotifyVO() {
    }

    public NotifyVO(SocialUserDO user) {
        //此处没有给content赋值是因为推送，不需要显示，推送后点击未读列表查询就行
        this.user = new NotifyUserVO(user, null);
        this.type = NotifyType.comment;
    }

    public NotifyVO(NotifyDO notify, SocialUserDO user, MessageReceiveDO messageReceive, ChatUserDO chatUser, ChatDO chatDO) {
        this(user);
        this.chat = SocialChatROFactory.getChatRO(chatDO, chatUser, messageReceive);
        this.receiveUserId = notify.getReceiveUserId();
        this.type = notify.getType();
        //此处没有给content赋值是因为推送，不需要显示，推送后点击未读列表查询就行
    }

    public NotifyVO(ChatDO chat, SocialUserDO user, MessageDO message) {
        this(user);
        this.chat = SocialChatROFactory.getChatRO(chat, message);
        this.type = NotifyType.message;
    }

    public TextMessage toMessage() {
        try {
            return new TextMessage(JsonUtil.objectMapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}