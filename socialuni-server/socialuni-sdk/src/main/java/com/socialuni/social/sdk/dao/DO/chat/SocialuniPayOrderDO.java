package com.socialuni.social.sdk.dao.DO.chat;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;


/**
 * 获取用户联系方式的记录，记录谁获取的谁
 */
@Data
@Table(name = "s_get_user_contact_record",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "beUserId"})
)
@Entity
@NoArgsConstructor
public class SocialuniPayOrderDO extends SocialuniBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer beUserId;

    public SocialuniPayOrderDO(Integer userId, Integer beUserId) {
        this.userId = userId;
        this.beUserId = beUserId;
    }
}