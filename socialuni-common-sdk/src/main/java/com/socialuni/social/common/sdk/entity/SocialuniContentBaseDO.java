package com.socialuni.social.common.sdk.entity;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public class SocialuniContentBaseDO extends SocialuniUserInfoBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    public SocialuniContentBaseDO(Integer userId) {
        super(userId);
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}