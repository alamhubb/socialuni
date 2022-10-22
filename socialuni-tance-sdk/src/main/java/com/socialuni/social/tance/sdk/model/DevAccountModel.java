package com.socialuni.social.tance.sdk.model;

import com.socialuni.social.common.repository.CommonContentBaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * 开发者账户
 */
@Data
public class DevAccountModel extends CommonContentBaseDO implements Serializable {
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
}