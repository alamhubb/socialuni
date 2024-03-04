package com.socialuni.social.common.sdk.dao.DO;


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
@Table(name = "s_match_request", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "beUserId"}))
public class MatchRequestDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer beUserId;

    private String status;

    private Date createTime = new Date();

    private Date updateTime;
}
