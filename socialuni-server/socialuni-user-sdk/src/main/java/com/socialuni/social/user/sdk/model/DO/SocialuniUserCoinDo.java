package com.socialuni.social.user.sdk.model.DO;


import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩展类
@Entity
@Table(name = "s_user_coin",
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@NoArgsConstructor
public class SocialuniUserCoinDo extends SocialuniContentBaseDO {
    private Integer shell;
}