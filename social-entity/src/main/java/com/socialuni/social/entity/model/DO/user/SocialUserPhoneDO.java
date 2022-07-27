package com.socialuni.social.entity.model.DO.user;

import com.socialuni.social.entity.model.DO.user.base.SocialUserPhoneBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;


@Entity
@Table(name = "s_user_phone",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "phoneNum"),
                @Index(columnList = "status"),
        }
)
@Data
@NoArgsConstructor
public class SocialUserPhoneDO extends SocialUserPhoneBaseDO implements Serializable {
    public SocialUserPhoneDO(Integer userId, String phoneCountryCode, String phoneNum) {
       super(userId,phoneCountryCode,phoneNum);
    }
}
