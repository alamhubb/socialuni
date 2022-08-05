package com.socialuni.center.web.entity;

import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.manage.ThirdUserTokenManage;
import com.socialuni.center.web.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
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
