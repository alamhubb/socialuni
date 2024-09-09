package com.socialuni.social.common.api.entity;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

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

    public SocialuniContentBaseDO(Long userId) {
        super(userId);
        this.sequenceNum = 0;
        this.status = SocialuniCommonStatus.enable;
    }

    public Integer sequencePlus(){
        return ++this.sequenceNum;
    }
}