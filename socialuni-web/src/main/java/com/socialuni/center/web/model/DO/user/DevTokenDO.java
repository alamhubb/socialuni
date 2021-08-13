package com.socialuni.center.web.model.DO.user;

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

    private Integer userId;

    private Date createDate;

    public DevTokenDO() {
    }

    public DevTokenDO(String tokenCode, Integer userId) {
        this.tokenCode = tokenCode;
        this.userId = userId;
        this.createDate = new Date();
    }
}
