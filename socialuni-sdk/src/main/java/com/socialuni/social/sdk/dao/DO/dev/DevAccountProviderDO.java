package com.socialuni.social.sdk.dao.DO.dev;

import com.socialuni.social.sdk.dao.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "s_dev_account_provider",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "devId"),
                @Index(columnList = "appId"),
                @Index(columnList = "platform"),
                @Index(columnList = "mpType")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"appId", "platform", "mpType"})
        })
@NoArgsConstructor
public class DevAccountProviderDO extends CommonContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer devId;
    @Column(nullable = false)
    private String appId;
    @Column(nullable = false)
    private String appName;

    //目前只支持小程序，未来支持app，记录app
    @Column(nullable = false, columnDefinition = "varchar(20) default ''")
    private String platform;

    @Column(nullable = false, columnDefinition = "varchar(20) default ''")
    private String mpType;

    //内容类型，id，关联商户，关联用户id
    /*@Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String avatarUrl;*/

    //对应三方的统一页面
    /*@Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String threePath;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String threeTalkPath;*/

    public DevAccountProviderDO(Integer devId, String platform, String mpType) {
        this.devId = devId;
        this.platform = platform;
        this.mpType = mpType;
    }
}
