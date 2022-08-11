package com.socialuni.sdk.model.DO.chat;

import com.socialuni.social.constant.CommonStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 获取用户联系方式的记录，记录谁获取的谁
 */
@Data
@Table(name = "user_contact", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "beUserId", "type"}))
@Entity
public class UserContactDO implements Serializable {
    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer beUserId;

    private Date createTime;
    private Date updateTime;

    //用户状态，暂未使用正常，封禁
    private String status;
    private String type;

    public UserContactDO() {
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
        this.status = CommonStatus.enable;
    }

    public UserContactDO(Integer userId, Integer beUserId, String type) {
        this();
        this.userId = userId;
        this.beUserId = beUserId;
        this.type = type;
    }
}