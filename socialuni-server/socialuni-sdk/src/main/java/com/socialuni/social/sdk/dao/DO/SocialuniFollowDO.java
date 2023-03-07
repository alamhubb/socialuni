package com.socialuni.social.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_follow",
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
public class SocialuniFollowDO extends SocialuniContentBaseDO {
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
