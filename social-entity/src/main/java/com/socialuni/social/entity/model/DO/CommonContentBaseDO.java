package com.socialuni.social.entity.model.DO;

import com.socialuni.constant.CommonStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class CommonContentBaseDO extends CommonBaseDO {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date updateTime;

    public CommonContentBaseDO() {
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}