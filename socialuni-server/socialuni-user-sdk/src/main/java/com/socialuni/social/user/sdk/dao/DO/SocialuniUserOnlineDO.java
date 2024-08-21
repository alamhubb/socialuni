package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name = "s_user_oneline",
        indexes = {
                @Index(columnList = "userId"),
        }
)
public class SocialuniUserOnlineDO extends SocialuniUserInfoBaseDO {

}
