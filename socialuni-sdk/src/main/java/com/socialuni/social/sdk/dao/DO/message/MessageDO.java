package com.socialuni.social.sdk.dao.DO.message;


import com.socialuni.social.sdk.constant.socialuni.MessageContentType;
import com.socialuni.social.sdk.constant.socialuni.MessageReadStatus;
import com.socialuni.social.sdk.constant.socialuni.MessageType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.common.sdk.entity.SocialuniUnionContentBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
@Entity
@Table(
        name = "s_message",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
public class MessageDO extends SocialuniUnionContentBaseDO implements Serializable {
    private Integer chatId;
    //官方，普通
    private String type;
    //图文，图片，文字，视频，这种类型，内容类型
    private String messageContentType;
    private String readStatus;
    //有多少人已读
    private Integer readNum;


    public MessageDO() {
    }

    public MessageDO(Integer chatId, String content, Integer userId) {
        super(userId, SocialuniContentType.message, content);
        this.chatId = chatId;
        //自己的chatUser，记录自己的未读消息数量，和是否置顶了，生成chatVo时使用
        this.readStatus = MessageReadStatus.sending;
        this.readNum = 0;
        this.type = MessageType.simple;
        this.messageContentType = MessageContentType.text;
    }

    public MessageDO(Integer chatId, String content, Integer userId, String type) {
        this(chatId, content, userId);
        this.type = type;
    }
}
