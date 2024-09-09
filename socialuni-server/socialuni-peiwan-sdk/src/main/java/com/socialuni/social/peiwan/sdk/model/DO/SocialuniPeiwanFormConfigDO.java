package com.socialuni.social.peiwan.sdk.model.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
public class SocialuniPeiwanFormConfigDO extends SocialuniUserInfoBaseDO {
    String configName;
    String configType;
}
