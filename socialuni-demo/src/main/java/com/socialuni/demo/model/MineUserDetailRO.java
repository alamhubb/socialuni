package com.socialuni.demo.model;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import lombok.Data;

@Data
public class MineUserDetailRO extends CenterMineUserDetailRO {
    //是否已绑定社交联盟账号
    boolean bindedSocialuni;
    String socialuniNickname;

    public MineUserDetailRO(CenterMineUserDetailRO centerMineUserDetailRO) {
        super(centerMineUserDetailRO);
    }
}
