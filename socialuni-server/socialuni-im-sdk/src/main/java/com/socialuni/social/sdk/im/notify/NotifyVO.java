package com.socialuni.social.sdk.im.notify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.sdk.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.Data;
import org.springframework.web.socket.TextMessage;

/**
 * @author qinkaiyuan
 * @date 2019-06-12 22:21
 */
@Data
public class NotifyVO<T> {
    //加入一列作为外键
    private ChatRO chat;

    private NotifyUserVO user;
    private Integer receiveUserId;

    private String type;
    private String chatId;
    private T data;

    public NotifyVO() {
    }

    public NotifyVO(SocialuniUserDo user) {
        //此处没有给content赋值是因为推送，不需要显示，推送后点击未读列表查询就行
        this.user = new NotifyUserVO(user);
        this.type = NotifyType.comment;
    }

    public NotifyVO(SocialuniUserDo user, String type, T data) {
        //此处没有给content赋值是因为推送，不需要显示，推送后点击未读列表查询就行
        this.user = new NotifyUserVO(user);
        this.type = type;
        this.data = data;
    }

    public NotifyVO(SocialuniChatDO chat, SocialuniUserDo user, SocialuniMessageDO message) {
        this(user);
        this.chat = SocialChatROFactory.getChatROByQueryChat(chat, message);
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
