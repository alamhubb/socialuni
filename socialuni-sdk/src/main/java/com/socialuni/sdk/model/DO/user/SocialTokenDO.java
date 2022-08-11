package com.socialuni.sdk.model.DO.user;

import com.socialuni.sdk.model.DO.user.base.CommonTokenBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

//为什么使用s_token
@Entity
@Table(name = "s_token",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
        },
        //手机号唯一
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "token"),
        }
)
@Data
public class SocialTokenDO extends CommonTokenBaseDO implements Serializable {
}