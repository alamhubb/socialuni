package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_user_follow",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
                @Index(columnList = "status"),
                @Index(columnList = "updateTime"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"userId", "beUserId"}),
        }
)
@EqualsAndHashCode(callSuper = true)
public class SocialuniUserFollowDO extends SocialuniUserContactBaseDO {
}
