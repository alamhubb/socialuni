package com.socialuni.social.user.sdk.dao.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserCoinDo extends SocialuniContentBaseDO {
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer coin;
}