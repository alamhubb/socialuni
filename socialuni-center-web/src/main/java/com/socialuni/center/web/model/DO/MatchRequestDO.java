package com.socialuni.center.web.model.DO;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-06-02 16:07
 */
@Data
@Entity
@Table(name = "match_request", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "receiveUserId"}))
public class MatchRequestDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer receiveUserId;

    private String status;

    private Date createTime = new Date();

    private Date updateTime;
}
