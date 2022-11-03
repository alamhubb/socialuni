package com.socialuni.social.common.sdk.model;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import lombok.Data;

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