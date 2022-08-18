package com.socialuni.center.qingchi;

import com.socialuni.sdk.model.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Entity
@Table(name = "qingchi_account",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"provider", "unionId"}),
                //根据平台和provider和userid获取用户，推送的时候使用？为了确保平台+渠道+user唯一
                @UniqueConstraint(columnNames = {"provider", "userId"}),
        }
)
public class QingchiUserAccountDO extends CommonContentBaseDO implements Serializable {
        @Column(nullable = false, updatable = false)
        private Integer userId;
        //使用哪个平台注册的
        private String platform;
        private String provider;
        private String unionId;
        //对应三方的token
        private String sessionKey;
}