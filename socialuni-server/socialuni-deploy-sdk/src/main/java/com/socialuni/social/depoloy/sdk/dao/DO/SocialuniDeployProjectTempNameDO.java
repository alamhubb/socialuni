package com.socialuni.social.depoloy.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_deploy_project_temp_name",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"name"})
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniDeployProjectTempNameDO extends SocialuniContentBaseDO {
    @Column(nullable = false)
    private String name;
}
