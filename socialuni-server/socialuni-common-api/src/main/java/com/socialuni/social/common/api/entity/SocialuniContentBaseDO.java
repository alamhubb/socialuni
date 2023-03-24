package com.socialuni.social.common.api.entity;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class SocialuniContentBaseDO extends SocialuniUserInfoBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    public SocialuniContentBaseDO() {
        this.updateTime = super.getCreateTime();
        this.status = SocialuniCommonStatus.init;
    }

    public SocialuniContentBaseDO(Integer userId) {
        super(userId);
        this.updateTime = super.getCreateTime();
        this.status = SocialuniCommonStatus.init;
    }
}