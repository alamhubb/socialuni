package com.socialuni.center.web.model.DO;

import com.socialuni.social.sdk.constant.DevAccountType;
import com.socialuni.social.sdk.constant.GenderType;
import com.socialuni.constant.ConstStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 开发者账户
 */
@Data@Entity
@Table(name = "dev_account",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "mpQqAppId"),
                @Index(columnList = "mpWxAppId"),
                @Index(columnList = "realName"),
                @Index(columnList = "appName"),
                @Index(columnList = "updateTime"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"secretKey"}),
                @UniqueConstraint(columnNames = {"devNum"}),
                @UniqueConstraint(columnNames = {"phoneNum"}),
        }
)
public class DevAccountDO {
    //开发者id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //密钥
    @Column(nullable = false)
    private String secretKey;
    @Column(nullable = false)
    private Long devNum;
    //个人身份证，企业税号
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String identityNum;
    @Column(nullable = false, columnDefinition = "varchar(255) default '" + GenderType.all + "'")
    private String appGenderType;
    //手机号
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String mpQqAppId;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String mpWxAppId;

    //类型，帐户类型，个人，企业
    @Column(nullable = false, columnDefinition = "varchar(20) default '" + DevAccountType.personal + "'")
    private String type;

    //个人或企业名称
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String realName;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String appName;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String avatarUrl;
    //api调用次数
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer callApiCount;

    //启用，还是失效
    @Column(nullable = false, columnDefinition = "varchar(20) default '" + ConstStatus.enable + "'")
    private String status;
    //创建时间
    @Column(nullable = false)
    private Date createTime;
    @Column(nullable = false)
    private Date updateTime;

    //内容类型，id，关联商户，关联用户id

    //对应三方的统一页面
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String threePath;
    @Column(nullable = false, columnDefinition = "varchar(255) default ''")
    private String threeTalkPath;

}