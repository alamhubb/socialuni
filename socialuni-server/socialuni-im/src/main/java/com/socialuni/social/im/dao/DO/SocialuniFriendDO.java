package com.socialuni.social.im.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "s_user_friend",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"userId", "beUserId"})
        }
)
@Data
@NoArgsConstructor
public class SocialuniFriendDO extends SocialuniUserContactBaseDO {


}