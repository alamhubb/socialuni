package com.socialuni.social.entity.model.DO.user.base;

import com.socialuni.social.entity.model.DO.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
@NoArgsConstructor
public class SocialUserPhoneBaseDO extends CommonContentBaseDO implements Serializable {
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
