package com.socialuni.sdk.entity;

import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.manage.ThirdUserTokenManage;
import com.socialuni.sdk.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class CenterLoginEntity {
    @Resource
    private ThirdUserTokenManage thirdUserTokenManage;

    public SocialLoginRO<CenterMineUserDetailRO> getCenterMineUserDetailROBySocialLoginRO(SocialUserDO mineUser, SocialLoginRO<SocialMineUserDetailRO> socialLoginRO) {
        SocialMineUserDetailRO socialMineUserDetailRO = socialLoginRO.getUser();

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerMineUserDetailRO.getId().toString().toString(), DevAccountUtils.getDevIdNotNull(), socialMineUserDetailRO.getId());

        return new SocialLoginRO<>(tokenDO.getToken(), centerMineUserDetailRO);
    }
}
