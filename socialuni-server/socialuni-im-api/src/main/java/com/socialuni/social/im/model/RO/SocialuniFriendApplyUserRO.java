package com.socialuni.social.im.model.RO;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import lombok.Data;

@Data
public class SocialuniFriendApplyUserRO extends SocialuniUserRO {
    // 待同意
    // 添加成功
    // 已同意
    // 已拒绝
    String friendApplyStatus;
    String applyMsg;

    public SocialuniFriendApplyUserRO(SocialuniUserRO userRO) {
        super(userRO);
    }
}
