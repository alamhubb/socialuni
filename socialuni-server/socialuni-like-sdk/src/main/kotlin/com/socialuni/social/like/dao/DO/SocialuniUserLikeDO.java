package com.socialuni.social.like.dao.DO;


import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩展类
@Entity
@Table(name = "s_user_like",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
                @Index(columnList = "status"),
                @Index(columnList = "updateTime"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"userId", "beUserId"}),
        })
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserLikeDO extends SocialuniUserContactBaseDO {
}