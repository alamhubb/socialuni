package com.socialuni.sdk.dao.DO.notify;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "s_push_message")
public class PushMessageDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer notifyId;

    private Date createTime;

    private String platform;

    //todo 要考虑长度，考虑是否为null考虑关联关系，考虑唯一
    @Column(length = 2000)
    private String content;
    @Column(length = 2000)
    private String result;

    public PushMessageDO() {
    }

    public PushMessageDO(Integer notifyId, String content, String result, String platform) {
        this.notifyId = notifyId;
        this.createTime = new Date();
        this.content = content;
        this.result = result;
        this.platform = platform;
    }
}
