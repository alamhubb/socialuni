package com.socialuni.social.entity.model.DO.user;

import com.socialuni.social.entity.model.DO.user.base.CommonUserAccountBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Entity
@Table(name = "s_account",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "devId"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"provider", "unionId", "devId"}),
                //根据平台和provider和userid获取用户，推送的时候使用？为了确保平台+渠道+user唯一
                @UniqueConstraint(columnNames = {"provider", "userId", "devId"}),
                @UniqueConstraint(columnNames = {"provider", "appOpenId", "devId"}),
                @UniqueConstraint(columnNames = {"provider", "mpOpenId", "devId"}),
        }
)
public class SocialUserAccountDO extends CommonUserAccountBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer devId;
}