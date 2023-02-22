package com.socialuni.social.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_user_get_contact_record",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
                @Index(columnList = "status"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"userId", "beUserId"}),
        }
)
public class SocialuniUserGetContactRecordDO extends SocialuniUserContactBaseDO {
}
