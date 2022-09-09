package com.socialuni.sdk.logic.domain.login;

import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.manage.TokenManage;
import com.socialuni.sdk.logic.entity.user.SocialProviderLoginEntity;
import com.socialuni.sdk.dao.DO.user.SocialTokenDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Component
public class SocialProviderLoginDomain {
    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;
    @Resource
    TokenManage tokenManage;

    @Transactional
    public SocialLoginRO<SocialuniMineUserDetailRO> providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        SocialuniUserDO mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        SocialuniMineUserDetailRO userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
