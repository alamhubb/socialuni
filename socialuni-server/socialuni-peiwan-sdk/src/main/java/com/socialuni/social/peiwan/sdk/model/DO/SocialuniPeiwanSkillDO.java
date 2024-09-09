package com.socialuni.social.peiwan.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "s_peiwan_skill",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "skillName"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId"),
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniPeiwanSkillDO extends SocialuniContentBaseDO {

    String skillName = null;
}
