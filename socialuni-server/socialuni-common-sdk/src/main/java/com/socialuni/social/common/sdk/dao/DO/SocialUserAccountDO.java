package com.socialuni.social.common.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUpdateTimeContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Entity
@Table(name = "s_user_account",
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"userId"}),
                @UniqueConstraint(columnNames = {"userName"}),
        }
)
@NoArgsConstructor
public class SocialUserAccountDO extends SocialuniUpdateTimeContentBaseDO implements Serializable {

        String userName;
        String password;

        public SocialUserAccountDO(Integer userId) {
                super(userId);
        }
}