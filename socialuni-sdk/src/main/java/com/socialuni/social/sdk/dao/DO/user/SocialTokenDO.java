package com.socialuni.social.sdk.dao.DO.user;

import com.socialuni.social.common.repository.CommonContentBaseDO;
import com.socialuni.social.sdk.constant.socialuni.DateTimeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//为什么使用s_token
@Entity
@Table(name = "s_token",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "devId"),
        },
        //手机号唯一
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "token"),
        }
)
@Data
@NoArgsConstructor
public class SocialTokenDO extends CommonContentBaseDO implements Serializable {
        @Column(nullable = false, updatable = false)
        private Integer userId;
        @Column(nullable = false, updatable = false)
        private Integer devId;

        @Column(nullable = false, updatable = false)
        private String token;

        @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
        private Date expiredTime;

        public SocialTokenDO(Integer userId, String token) {
                this.userId = userId;
                this.token = token;
                this.expiredTime = new Date(new Date().getTime() + DateTimeType.quarter);
        }
}