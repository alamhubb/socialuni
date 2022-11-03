package com.socialuni.social.user.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class SocialUserPhoneModel extends SocialuniContentBaseModel implements Serializable {
    private String phoneCountryCode;
    private String phoneNum;
    private Integer devId;

    public SocialUserPhoneModel(Integer userId, String phoneCountryCode, String phoneNum, Integer devId) {
        this.setUserId(userId);
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNum = phoneNum;
        this.devId = devId;
    }
}
