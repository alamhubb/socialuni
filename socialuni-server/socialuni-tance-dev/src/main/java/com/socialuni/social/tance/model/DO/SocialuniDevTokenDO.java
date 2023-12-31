package com.socialuni.social.tance.model.DO;

import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//为什么使用s_token
@Entity
@Table(name = "s_dev_token",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "devId"),
        },
        //手机号唯一
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "token"),
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniDevTokenDO extends SocialuniContentBaseDO implements Serializable {
        @Column(nullable = false, updatable = false)
        private Integer devId;

        @Column(nullable = false, updatable = false)
        private String token;

        @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
        private Date expiredTime;

        public SocialuniDevTokenDO(String token) {
                this.token = token;
                //半小时有效期
                this.expiredTime = new Date(new Date().getTime() + DateTimeType.halfHour);
        }
}