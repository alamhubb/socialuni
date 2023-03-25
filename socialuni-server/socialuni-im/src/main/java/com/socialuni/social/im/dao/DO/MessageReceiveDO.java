package com.socialuni.social.im.dao.DO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socialuni.social.common.api.enumeration.ConstStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "message_receive", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"receiveUserId", "messageId"})
})
@ToString(exclude = "message")
//发送出去的消息表，接收人是谁，基于哪个chatUser
public class MessageReceiveDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //置顶标识
    private String status;
    private Boolean isRead;
    /*
        //自己的chatUser，记录自己的未读消息数量，和是否置顶了，生成chatVo时使用
        private Integer chatUserId;*/
    //是否自己的
    private Boolean isMine;

    private Long messageId;

    //
    private Integer receiveUserId;
    private Integer userId;
    /**
     * 这个用来记录chat的昵称，和头像的。只有私聊的状态下才有
     */
    private Long chatUserId;

    private Date createTime;

    private Date updateTime;

    public MessageReceiveDO() {
    }

    public MessageReceiveDO(Long chatUserId, Integer userId, Integer receiveUserId, Long messageId) {
        Date curDate = new Date();
        this.chatUserId = chatUserId;
        this.userId = userId;
        this.receiveUserId = receiveUserId;
        this.messageId = messageId;
        this.status = ConstStatus.init;
        //接受消息的人看的
        this.isRead = false;
        this.isMine = false;
        this.createTime = curDate;
        this.updateTime = curDate;
    }
}
