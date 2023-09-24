package com.socialuni.social.common.api.entity;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class SocialuniContentBaseDO extends SocialuniUpdateTimeContentBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;
    private String type;

    public SocialuniContentBaseDO() {
        this.status = SocialuniCommonStatus.enable;
    }

    public SocialuniContentBaseDO(Integer userId) {
        super(userId);
        this.status = SocialuniCommonStatus.enable;
    }
}