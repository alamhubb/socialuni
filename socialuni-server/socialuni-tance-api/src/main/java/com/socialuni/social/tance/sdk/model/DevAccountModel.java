package com.socialuni.social.tance.sdk.model;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.enumeration.ConstStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 开发者账户
 */
@Data
public class DevAccountModel extends SocialuniBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    //密钥
    private Integer userId;
    private String secretKey;
    private Long devNum;
    //个人身份证，企业税号
    private String identityNum;
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
    private String type;

    //个人或企业名称
    private String realName;

    //api调用次数
    private Integer callApiCount;

    public DevAccountModel() {
        this.status = ConstStatus.enable;
        this.updateTime = this.getCreateTime();
    }
}