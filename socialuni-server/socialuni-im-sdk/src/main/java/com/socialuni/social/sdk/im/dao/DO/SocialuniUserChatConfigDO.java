package com.socialuni.social.sdk.im.dao.DO;

import com.qingchi.qing.jpa.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "s_im_user_chat_config",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"userId"})
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniUserChatConfigDO extends SocialuniUserInfoBaseDO {

    //是否接收陌生人消息
    Boolean allowStrangerMsg;
    //陌生人给你发消息需要的金币数量
    Integer strangerMsgCoin;

    public SocialuniUserChatConfigDO() {
        this.allowStrangerMsg = true;
        this.strangerMsgCoin = 0;
    }
}
