package com.socialuni.social.im.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "s_user_friend_apply_record",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
        }
)
@Data
@NoArgsConstructor
public class SocialuniFriendApplyRecordDO extends SocialuniUserContactBaseDO {


}