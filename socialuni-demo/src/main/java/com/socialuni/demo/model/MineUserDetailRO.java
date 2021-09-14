package com.socialuni.demo.model;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import lombok.Data;

@Data
public class MineUserDetailRO extends CenterMineUserDetailRO {
    //是否已绑定社交联盟账号
    boolean bindSocialuni;

    public MineUserDetailRO(CenterMineUserDetailRO centerMineUserDetailRO) {
        super(centerMineUserDetailRO);
    }
}
