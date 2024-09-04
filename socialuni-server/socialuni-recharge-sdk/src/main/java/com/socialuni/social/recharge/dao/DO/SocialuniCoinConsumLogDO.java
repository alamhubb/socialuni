package com.socialuni.social.recharge.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 获取用户联系方式的记录，记录谁获取的谁
 */
@Data
@Table(name = "s_coin_consum_order", indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "beUserId"),
        @Index(columnList = "type"),
})
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniCoinConsumLogDO extends SocialuniContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer beUserId;

    public SocialuniCoinConsumLogDO(Long userId, Integer beUserId) {
        this.userId = userId;
        this.beUserId = beUserId;
    }
}