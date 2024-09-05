package com.socialuni.social.tance.dev.model;

import lombok.Data;

import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-19 22:27
 */
@Data
public class DevTokenModler {

    private Integer id;

    private String tokenCode;

    private Integer devId;

    private Date createDate;

    public DevTokenModler() {
    }

    public DevTokenModler(String tokenCode, Integer devId) {
        this.tokenCode = tokenCode;
        this.devId = devId;
        this.createDate = new Date();
    }
}
