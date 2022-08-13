package com.socialuni.sdk.model.DO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Entity
@Table(name = "third_content_uid",
        indexes = {
                @Index(columnList = "contentType"),
                @Index(columnList = "contentId"),
                @Index(columnList = "status"),
                @Index(columnList = "devId"),
                @Index(columnList = "userId"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"unionId"}),
                @UniqueConstraint(columnNames = {"contentId", "contentType", "devId", "userId", "status", "beginTime"})
        }
)
public class UnionIdDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unionId;
    //内容类型
    private String contentType;
    //内容的唯一id
    private Integer contentId;
    //开发者id
    private Integer devId;
    //用户id，分配给哪个用户的
    private Integer userId;

    //启用，还是失效
    private String status;
    //创建时间
    private Date createTime;
    //用来判断是否没有失效时间，不使用expiredTime因为还需要计算，beginTime直接等于=new date
    private Date beginTime;
    //创建时间
    private Date updateTime;
    //有效周期，单位毫秒
    private Long validTime;
    //失效时间
    private Date expiredTime;
    private String ip;

    //内容类型，id，关联商户，关联用户id
}