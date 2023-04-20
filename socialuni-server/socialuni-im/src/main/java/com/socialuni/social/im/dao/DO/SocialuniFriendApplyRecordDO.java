package com.socialuni.social.im.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(name = "s_user_friend_apply_record",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
                @Index(columnList = "status"),
                @Index(columnList = "type"),
        }
)
@Data
@NoArgsConstructor
public class SocialuniFriendApplyRecordDO extends SocialuniUserContactBaseDO {
    String applyMsg;

    public SocialuniFriendApplyRecordDO(Integer userId, Integer beUserId, String applyMsg, String applyType) {
        super(userId, beUserId);
        this.applyMsg = applyMsg;
        this.setStatus(SocialuniAddFriendStatus.init);
        this.setType(applyType);
    }
}