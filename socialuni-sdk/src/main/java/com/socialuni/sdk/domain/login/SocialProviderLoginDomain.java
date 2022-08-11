package com.socialuni.sdk.domain.login;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.manage.TokenManage;
import com.socialuni.sdk.entity.user.SocialProviderLoginEntity;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
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
    public SocialLoginRO<SocialMineUserDetailRO> providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        SocialUserDO mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        SocialMineUserDetailRO userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
