package com.socialuni.social.sdk.dao.DO.bussiness;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
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
public class SocialuniGetUserContactRecordDO extends SocialuniUserContactBaseDO implements Serializable {

    @Column(nullable = false)
    String contactInfo;

    public SocialuniGetUserContactRecordDO(Integer userId, Integer beUserId) {
        super(userId, beUserId);
    }
}