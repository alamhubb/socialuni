package com.socialuni.social.tance.entity;

import com.socialuni.social.tance.sdk.model.DevTokenModler;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-19 22:27
 */
@Entity
@Table(name = "s_dev_token")
@Data
public class DevTokenDo extends DevTokenModler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tokenCode;

    private Integer devId;

    private Date createDate;

    public DevTokenDo() {
    }

    public DevTokenDo(String tokenCode, Integer devId) {
        this.tokenCode = tokenCode;
        this.devId = devId;
        this.createDate = new Date();
    }
}
