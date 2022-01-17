package com.socialuni.social.entity.model.DO.dev;

import com.socialuni.social.entity.model.DO.user.base.CommonTokenBaseDO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "third_user_token",
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
public class ThirdUserTokenDO extends CommonTokenBaseDO {
    @Column(nullable = false)
    private Integer devId;
    private String thirdUserId;

    public ThirdUserTokenDO() {
    }

    public ThirdUserTokenDO(Integer userId, String token, Integer devId,String thirdUserId) {
        super(userId, token);
        this.devId = devId;
        this.thirdUserId = thirdUserId;
    }
}