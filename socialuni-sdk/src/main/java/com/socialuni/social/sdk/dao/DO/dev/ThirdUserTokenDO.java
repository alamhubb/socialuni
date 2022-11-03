package com.socialuni.social.sdk.dao.DO.dev;

import com.socialuni.social.common.sdk.entity.SocialuniContentBaseDO;
import com.socialuni.social.sdk.constant.socialuni.DateTimeType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "s_third_user_token",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "devId"),
                @Index(columnList = "thirdUserId"),
        },
        //手机号唯一
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "token"),
        }
)
@Data
public class ThirdUserTokenDO extends SocialuniContentBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer devId;
    private String thirdUserId;
    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private String token;

    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date expiredTime;

    public ThirdUserTokenDO() {
        this.expiredTime = new Date(new Date().getTime() + DateTimeType.quarter);
    }

    public ThirdUserTokenDO(Integer userId, String token, Integer devId,String thirdUserId) {
        this.userId = userId;
        this.token = token;
        this.devId = devId;
        this.thirdUserId = thirdUserId;
        this.expiredTime = new Date(new Date().getTime() + DateTimeType.quarter);
    }
}