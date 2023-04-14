package com.socialuni.social.im.model.message.notify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.message.MessageDO;
import com.socialuni.social.im.dao.DO.message.MessageReceiveDO;
import com.socialuni.social.im.enumeration.NotifyType;
import com.socialuni.social.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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

    public NotifyVO(SocialuniUserDo user) {
        //此处没有给content赋值是因为推送，不需要显示，推送后点击未读列表查询就行
        this.user = new NotifyUserVO(user, null);
        this.type = NotifyType.comment;
    }

    public NotifyVO(NotifyDO notify, SocialuniUserDo user, MessageReceiveDO messageReceive, ChatUserDO chatUser, SocialuniChatDO chatDO) {
        this(user);
        this.chat = SocialChatROFactory.getChatRO(chatDO, chatUser, messageReceive);
        this.receiveUserId = notify.getBeUserId();
        this.type = notify.getType();
        //此处没有给content赋值是因为推送，不需要显示，推送后点击未读列表查询就行
    }

    public NotifyVO(SocialuniChatDO chat, SocialuniUserDo user, MessageDO message) {
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
