package com.socialuni.social.common.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;


/**
 * 获取用户联系方式的记录，记录谁获取的谁
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "s_user_get_user_contact_record",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
        },
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "beUserId"})
)
@Entity
@NoArgsConstructor
public class SocialuniGetUserContactRecordDO extends SocialuniUserContactBaseDO implements Serializable {

    @Column(nullable = false)
    String contactInfo;

    public SocialuniGetUserContactRecordDO(Integer userId, Integer beUserId, String contactInfo) {
        super(userId, beUserId);
        this.contactInfo = contactInfo;
    }
}