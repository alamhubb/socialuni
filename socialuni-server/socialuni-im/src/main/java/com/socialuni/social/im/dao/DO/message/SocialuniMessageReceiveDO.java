package com.socialuni.social.im.dao.DO.message;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
@Entity
@Table(name = "s_im_message_receive", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"beUserId", "messageId"})
})
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//发送出去的消息表，接收人是谁，基于哪个chatUser
public class SocialuniMessageReceiveDO extends SocialuniUserContactBaseDO implements Serializable {

    //msg上存储了是否已撤回状态，以及发送的消息的状态，撤回和已删除冲突，有可能已删除，但是已撤回，一个字段记录不了两个状态
    private String msgStatus;
    //置顶标识，用类记录接收的状态
    private Boolean isRead;
    /*
        //自己的chatUser，记录自己的未读消息数量，和是否置顶了，生成chatVo时使用
        private Integer chatUserId;*/
    //是否自己的,如果有接收人id，应该指定是为自己的吧
    private Boolean isMine;

    //    @ManyToOne
//    private MessageDO message;
    private Integer messageId;

    /**
     * 这个用来记录chat的昵称，和头像的。只有私聊的状态下才有
     */
//    @ManyToOne
//    private
//    ChatUserDO chatUser;

    private Integer chatUserId;

    public SocialuniMessageReceiveDO(SocialuniChatUserDO chatUserDO, Integer sendUserId, Integer messageId) {
        super(sendUserId, chatUserDO.getUserId());
        this.chatUserId = chatUserDO.getId();
        this.messageId = messageId;
        this.isMine = sendUserId.equals(chatUserDO.getUserId());
        //接受消息的人看的,自己的默认已读
        this.isRead = this.isMine;
    }
}
