package com.qingchi.web.model.EO;

import com.qingchi.web.model.DO.UserDetailDO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import lombok.Data;

@Data
public class ProviderLoginEO {
    UserDO mineUser;
    UserDetailDO mineUserDetail;
    CenterMineUserDetailRO centerMineUserDetailRO;

    public ProviderLoginEO(UserDO mineUser, UserDetailDO mineUserDetail, CenterMineUserDetailRO centerMineUserDetailRO) {
        this.mineUser = mineUser;
        this.mineUserDetail = mineUserDetail;
        this.centerMineUserDetailRO = centerMineUserDetailRO;
    }
}
