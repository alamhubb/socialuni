package com.socialuni.social.user.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩展类
@Entity
@Table(name = "s_user_social_coin",
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@NoArgsConstructor
public class SocialuniUserSocialCoinDO extends SocialuniUserInfoBaseDO {
    @Column(nullable = false)
    private Integer socialCoin;
}