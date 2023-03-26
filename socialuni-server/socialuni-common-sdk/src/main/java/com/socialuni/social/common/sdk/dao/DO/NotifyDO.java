package com.socialuni.social.common.sdk.dao.DO;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data@Entity

@Table(name = "s_notify")
public class NotifyDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer beUserId;

    //加入一列作为外键
    private Integer messageReceiveId;

    //加入一列作为外键
    private Integer commentId;

    //加入一列作为外键
    private Integer reportId;

    //加入一列作为外键
    private Integer reportDetailId;

    //加入一列作为外键
    private Integer talkId;

    private String type;

    private Date createTime;

    private Boolean hasRead;

    public NotifyDO() {
        this.createTime = new Date();
        this.hasRead = false;
    }

    public NotifyDO(Integer userId, Integer beUserId) {
        this();
        this.userId = userId;
        this.beUserId = beUserId;
    }

    public NotifyDO(Integer userId, Integer beUserId, String type) {
        this(userId, beUserId);
        this.type = type;
    }

  /*  public NotifyDO(MessageReceiveDO msg) {
        this(msg.getUserId(), msg.getBeUserId(), NotifyType.message);
        this.messageReceiveId = msg.getId();
    }*/

    public NotifyDO(Integer userId, Integer beUserId, Integer commentId, Integer talkId, String type) {
        this(userId, beUserId, type);
        this.commentId = commentId;
// 理论上无用，都使用comment.getTalkId       this.talkId = talkId;
    }

    public NotifyDO(Integer userId, Integer beUserId, Integer reportId, String type) {
        this(userId, beUserId, type);
        this.reportId = reportId;
    }

    /*public NotifyDO(MessageReceiveDO msg, String notifyType) {
        this(msg);
        this.type = notifyType;
    }*/
}
