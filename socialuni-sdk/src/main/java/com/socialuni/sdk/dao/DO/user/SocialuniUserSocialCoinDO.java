package com.socialuni.sdk.dao.DO.user;

import com.socialuni.sdk.dao.DO.SocialContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//用户扩展类
@Entity
@Table(name = "s_user_social_coin",
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@NoArgsConstructor
public class SocialuniUserSocialCoinDO extends SocialContentBaseDO implements Serializable {
    private Integer userId;
    @Column(nullable = false)
    private Integer socialCoin;
}