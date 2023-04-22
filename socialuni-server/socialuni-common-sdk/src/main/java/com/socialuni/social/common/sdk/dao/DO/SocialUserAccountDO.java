package com.socialuni.social.common.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
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
@Table(name = "s_user_account",
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
                @UniqueConstraint(columnNames = {"provider", "appOpenId"}),
                @UniqueConstraint(columnNames = {"provider", "mpOpenId"}),
                @UniqueConstraint(columnNames = {"provider", "h5OpenId"}),
        }
)
@NoArgsConstructor
public class SocialUserAccountDO extends SocialuniContentBaseDO implements Serializable {
        //使用哪个平台注册的
        private String platform;
        private String provider;
        private String appOpenId;
        private String mpOpenId;
        private String h5OpenId;
        private String unionId;
        //对应三方的token
        private String sessionKey;

        private String nickname;
        private String avatar;
        private String gender;

        public SocialUserAccountDO(Integer userId) {
                super(userId);
        }
}