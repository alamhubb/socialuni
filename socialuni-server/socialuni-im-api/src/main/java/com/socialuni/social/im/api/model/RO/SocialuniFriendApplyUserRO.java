package com.socialuni.social.im.api.model.RO;

import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniFriendApplyUserRO extends SocialuniUserShowRO {
    // 待同意
    // 添加成功
    // 已同意
    // 已拒绝
    String friendApplyStatus;
    String applyMsg;
    Date createTime;

    public SocialuniFriendApplyUserRO(SocialuniUserShowRO userRO) {
        super(userRO);
    }
}
