package com.socialuni.social.common.model;

import com.socialuni.social.common.enumeration.CommonStatus;
import com.socialuni.social.common.repository.CommonBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
public class CommonContentBaseModel extends CommonBaseModel implements Serializable {
    private String status;
    private Date updateTime;

    public CommonContentBaseModel() {
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}