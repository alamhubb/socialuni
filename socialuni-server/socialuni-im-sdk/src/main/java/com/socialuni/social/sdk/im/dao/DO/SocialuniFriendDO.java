package com.socialuni.social.sdk.im.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "s_im_user_friend",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
                @Index(columnList = "status"),
        },
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = {"userId", "beUserId"})
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniFriendDO extends SocialuniUserContactBaseDO {

    public SocialuniFriendDO(Integer userId, Integer beUserId, String applyMsg) {
        super(userId, beUserId);
    }
}