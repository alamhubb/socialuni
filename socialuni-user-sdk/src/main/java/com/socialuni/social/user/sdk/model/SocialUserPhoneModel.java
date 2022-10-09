package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.repository.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class SocialUserPhoneModel extends CommonContentBaseDO implements Serializable {
    private Integer userId;
    private String phoneCountryCode;
    private String phoneNum;
    private Integer devId;

    public SocialUserPhoneModel(Integer userId, String phoneCountryCode, String phoneNum, Integer devId) {
        this.userId = userId;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNum = phoneNum;
        this.devId = devId;
    }
}
