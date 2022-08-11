package com.socialuni.sdk.model.DO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@MappedSuperclass
public class SocialCommonBaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    public SocialCommonBaseDO() {
        this.createTime = new Date();
    }
}