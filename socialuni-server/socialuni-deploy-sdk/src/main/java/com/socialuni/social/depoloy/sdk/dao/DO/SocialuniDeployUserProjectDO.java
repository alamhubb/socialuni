package com.socialuni.social.depoloy.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_deploy_user_project",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"projectName"})
        }
)
@Data
@NoArgsConstructor
public class SocialuniDeployUserProjectDO extends SocialuniContentBaseDO {
    @Column(nullable = false, updatable = false)
    private Integer userId;
    @Column(nullable = false)
    private String projectName;
}