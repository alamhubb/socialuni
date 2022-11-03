package com.socialuni.social.common.repository;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class CommonContentBaseDO extends CommonBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    public CommonContentBaseDO() {
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}