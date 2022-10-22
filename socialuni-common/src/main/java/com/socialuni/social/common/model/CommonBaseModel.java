package com.socialuni.social.common.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class CommonBaseModel implements Serializable {
    private Date createTime;

    public CommonBaseModel() {
        Date curDate = new Date();
        this.createTime = curDate;
    }
}