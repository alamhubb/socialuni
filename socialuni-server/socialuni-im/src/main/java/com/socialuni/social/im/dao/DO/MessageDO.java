package com.socialuni.social.im.dao.DO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qingchi.base.constant.CommonStatus;
import com.qingchi.base.constant.MessageContentType;
import com.qingchi.base.constant.MessageType;
import com.qingchi.base.constant.ReportContentType;
import com.qingchi.base.model.BaseModelDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "message")
public class MessageDO implements BaseModelDO, Serializable {
    //不需要有类型，chat什么类型，消息就是什么类型
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long chatId;

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

    public MessageDO(Long chatId, String content, Integer userId) {
        this.chatId = chatId;
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
        this.content = content;
        this.reportContentType = ReportContentType.message;
        //自己的chatUser，记录自己的未读消息数量，和是否置顶了，生成chatVo时使用
        this.status = CommonStatus.normal;
        this.readStatus = CommonStatus.sending;
        this.readNum = 0;
        this.type = MessageType.simple;
        this.contentType = MessageContentType.text;
        this.userId = userId;
        this.reportNum = 0;
    }

    public MessageDO(Long chatId, String content, Integer userId, String type) {
        this(chatId, content, userId);
        this.type = type;
    }

    @Override
    @JsonIgnore
    public Integer getDbId() {
        return this.getId().intValue();
    }
}
