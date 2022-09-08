package com.socialuni.sdk.logic.entity;

import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.logic.manage.ThirdUserTokenManage;
import com.socialuni.sdk.dao.DO.dev.ThirdUserTokenDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class CenterLoginEntity {
    @Resource
    private ThirdUserTokenManage thirdUserTokenManage;

    public SocialLoginRO<SocialuniMineUserDetailRO> getCenterMineUserDetailROBySocialLoginRO(SocialuniUserDO mineUser, SocialLoginRO<SocialMineUserDetailRO> socialLoginRO) {
        SocialMineUserDetailRO socialMineUserDetailRO = socialLoginRO.getUser();

        SocialuniMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerMineUserDetailRO.getId().toString().toString(), DevAccountUtils.getDevIdNotNull(), socialMineUserDetailRO.getId());

        return new SocialLoginRO<>(tokenDO.getToken(), centerMineUserDetailRO);
    }
}
