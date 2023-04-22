package com.socialuni.social.user.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

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
public class SocialuniUserFollowDO extends SocialuniContentBaseDO {
    /**
     * 主用户
     */
    @Column(nullable = false, updatable = false)
    private Integer userId;

    /**
     * 被关联用户
     */
    @Column(nullable = false, updatable = false)
    private Integer beUserId;
}
