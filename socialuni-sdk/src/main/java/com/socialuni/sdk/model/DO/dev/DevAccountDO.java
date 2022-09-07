package com.socialuni.sdk.model.DO.dev;

import com.socialuni.sdk.model.DO.base.CommonContentBaseDO;
import com.socialuni.sdk.constant.socialuni.DevAccountType;
import com.socialuni.sdk.constant.socialuni.GenderType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 开发者账户
 */
@Data@Entity
@Table(name = "s_dev_account",
        indexes = {
                @Index(columnList = "realName"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"secretKey"}),
                @UniqueConstraint(columnNames = {"devNum"}),
                @UniqueConstraint(columnNames = {"phoneNum"}),
                @UniqueConstraint(columnNames = {"socialuniId"}),
        }
)
public class DevAccountDO extends CommonContentBaseDO implements Serializable {
    //密钥
    @Column(nullable = false)
    private String secretKey;
    @Column(nullable = false)
    private Long devNum;
    //个人身份证，企业税号
    private String identityNum;
    @Column(nullable = false, columnDefinition = "varchar(255) default '" + GenderType.all + "'")
    private String appGenderType;
    private String appName;
    //手机号
    private String phoneNum;
    //联盟id，唯一标识，不得小于32位，全网唯一
    private String socialuniId;
    private String apiUrl;
    //调用子应用时传入的秘钥，由对方提供
    private String apiSecretKey;

    //类型，帐户类型，个人，企业
    @Column(nullable = false, columnDefinition = "varchar(20) default '" + DevAccountType.personal + "'")
    private String type;

    //个人或企业名称
    @Column(columnDefinition = "varchar(255) default ''")
    private String realName;

    //api调用次数
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer callApiCount;
}