package com.socialuni.social.common.api.entity;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class SocialuniContentBaseDO extends SocialuniUpdateTimeContentBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;
    private String type;
    //操作顺序id
    private Integer sequenceNum;

    public SocialuniContentBaseDO() {
        this.sequenceNum = 0;
        this.status = SocialuniCommonStatus.enable;
    }

    public SocialuniContentBaseDO(Integer userId) {
        super(userId);
        this.sequenceNum = 0;
        this.status = SocialuniCommonStatus.enable;
    }

    public Integer sequencePlus(){
        return ++this.sequenceNum;
    }
}