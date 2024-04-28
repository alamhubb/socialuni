package com.socialuni.social.friend.dao.DO

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO
import lombok.Data
import lombok.EqualsAndHashCode
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Data
@Entity
@Table(
    name = "s_user_follow",
    indexes = [Index(columnList = "userId"), Index(columnList = "beUserId"), Index(columnList = "status"), Index(
        columnList = "updateTime"
    )],
    uniqueConstraints = [UniqueConstraint(columnNames = ["userId", "beUserId"])]
)
@EqualsAndHashCode(callSuper = true)
class SocialuniUserFollowDO : SocialuniUserContactBaseDO()