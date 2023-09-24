package com.socialuni.social.peiwan.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "s_peiwan_form_config",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "configName"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "configName"),
        }
)
@Data
public class SocialuniPeiwanFormConfigDO extends SocialuniUserInfoBaseDO {
    String configName;
    String configType;
}
