package com.socialuni.social.entity.model.DO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class CommonBaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private Date createTime;

    public CommonBaseDO() {
        Date curDate = new Date();
        this.createTime = curDate;
    }
}