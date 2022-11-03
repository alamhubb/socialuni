package com.socialuni.social.sdk.dao.DO.chat;

import com.socialuni.social.common.dao.DO.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 获取用户联系方式的记录，记录谁获取的谁
 */
@Data
@Table(name = "s_get_user_contact_record",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
        },
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "beUserId"})
)
@Entity
@NoArgsConstructor
public class SocialuniGetUserContactRecordDO extends SocialuniUserInfoBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer beUserId;

    public SocialuniGetUserContactRecordDO(Integer userId, Integer beUserId) {
        super(userId);
        this.beUserId = beUserId;
    }
}