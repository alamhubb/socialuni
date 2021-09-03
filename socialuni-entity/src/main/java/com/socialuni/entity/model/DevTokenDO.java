package com.socialuni.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-19 22:27
 */@Entity
@Table(name = "dev_token")
@Data
public class DevTokenDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tokenCode;

    private Integer devId;

    private Date createDate;

    public DevTokenDO() {
    }

    public DevTokenDO(String tokenCode, Integer devId) {
        this.tokenCode = tokenCode;
        this.devId = devId;
        this.createDate = new Date();
    }
}
