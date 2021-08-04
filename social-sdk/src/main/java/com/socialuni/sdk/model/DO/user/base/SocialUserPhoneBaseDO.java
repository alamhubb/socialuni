package com.socialuni.sdk.model.DO.user.base;

import com.socialuni.sdk.model.DO.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
public class SocialUserPhoneBaseDO extends CommonContentBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    @Column(nullable = false)
    private String phoneCountryCode;
    @Column(nullable = false)
    private String phoneNum;

    public SocialUserPhoneBaseDO(Integer userId, String phoneCountryCode, String phoneNum) {
        this.userId = userId;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNum = phoneNum;
    }
}
