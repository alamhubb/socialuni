package com.socialuni.social.entity.model.DO.message;


import com.socialuni.constant.*;
import com.socialuni.social.entity.model.DO.CommonContentBaseDO;
import com.socialuni.social.entity.model.DO.base.BaseModelDO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
@Entity
@Table(name = "message")
public class MessageDO extends CommonContentBaseDO implements BaseModelDO {
    //为什么不存thirdUserId，因为根据userId+devId可以确认thirdUserId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private Integer chatId;

    private Date createTime;
    private Date updateTime;
    /**
     * 哪个评论的
     */
    private Integer userId;

    //状态
    private String status;
    //官方，普通
    private String type;
    //图文，图片，文字，视频，这种类型，内容类型
    private String contentType;
    private String reportContentType;
    private String readStatus;
    //有多少人已读
    private Integer readNum;
    /**
     * 删除原因
     */
    private String deleteReason;
    private Integer reportNum;

    private String violateType;

    public MessageDO() {
    }

    public MessageDO(Integer chatId, String content, Integer userId) {
        this.chatId = chatId;
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
        this.content = content;
        this.reportContentType = ContentType.message;
        //自己的chatUser，记录自己的未读消息数量，和是否置顶了，生成chatVo时使用
        this.status = MessageStatus.enable;
        this.readStatus = MessageReadStatus.sending;
        this.readNum = 0;
        this.type = MessageType.simple;
        this.contentType = MessageContentType.text;
        this.userId = userId;
        this.reportNum = 0;
    }

    public MessageDO(Integer chatId, String content, Integer userId, String type) {
        this(chatId, content, userId);
        this.type = type;
    }
}
