package com.socialuni.social.user.sdk.entity;

import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//用户扩展类
@Entity
@Table(name = "s_user_social_coin",
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        })
@Data
@NoArgsConstructor
public class SocialuniUserSocialCoinDo extends SocialuniUserSocialCoinModel {
    @Column(nullable = false)
    private Integer socialCoin;
}