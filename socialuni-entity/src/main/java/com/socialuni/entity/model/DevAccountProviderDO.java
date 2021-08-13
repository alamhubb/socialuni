package com.socialuni.entity.model;

import com.socialuni.social.constant.ConstStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dev_account_provider",
        indexes = {
                @Index(columnList = "status")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"appId", "platform", "mpType"})
        })
public class DevAccountProviderDO {
    //开发者id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer devId;
    @Column(nullable = false)
    private String appName;
    @Column(nullable = false)
    private String appId;

    //目前只支持小程序，未来支持app，记录app
    @Column(nullable = false, columnDefinition = "varchar(20) default ''")
    private String platform;

    @Column(nullable = false, columnDefinition = "varchar(20) default ''")
    private String mpType;
    //启用，还是失效
    @Column(nullable = false, columnDefinition = "varchar(20) default '" + ConstStatus.enable + "'")
    private String status;
    //创建时间
    @Column(nullable = false)
    private Date createTime;
    @Column(nullable = false)
    private Date updateTime;
}
