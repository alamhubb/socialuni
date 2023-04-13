package com.socialuni.social.im.dao.DO.message;


import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.im.enumeration.MessageContentType;
import com.socialuni.social.im.enumeration.MessageReadStatus;
import com.socialuni.social.im.enumeration.MessageType;
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
    //图文，图片，文字，视频，这种类型，内容类型
    private String messageContentType;
    private String readStatus;
    //有多少人已读
    private Integer readNum;

    public MessageDO() {
    }

    public MessageDO(Integer userId, String contentType, String content) {
        super(userId, contentType, content);
    }
}
