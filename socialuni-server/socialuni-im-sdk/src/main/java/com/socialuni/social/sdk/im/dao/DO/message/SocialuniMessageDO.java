package com.socialuni.social.sdk.im.dao.DO.message;


import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
@Entity
@Table(
        name = "s_im_message",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "userId"),
                @Index(columnList = "updateTime"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@EqualsAndHashCode(callSuper = true)
public class SocialuniMessageDO extends SocialuniUnionContentBaseDO implements Serializable {
    private Integer chatId;
    //官方，普通
    //图文，图片，文字，视频，这种类型，内容类型
    private String messageContentType;
    private String readStatus;
    //有多少人已读
    private Integer readNum;

    public SocialuniMessageDO() {
    }

    public SocialuniMessageDO(Integer userId, Integer unionId, String contentType, String content) {
        super(userId, unionId, contentType, content);
    }
}
