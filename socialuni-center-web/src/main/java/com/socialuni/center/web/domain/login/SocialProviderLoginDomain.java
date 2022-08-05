package com.socialuni.center.web.domain.login;

import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.center.web.manage.TokenManage;
import com.socialuni.center.web.entity.user.SocialProviderLoginEntity;
import com.socialuni.center.web.model.DO.user.TokenDO;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
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
        UserDO mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        SocialMineUserDetailRO userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        TokenDO socialUserTokenDO = tokenManage.create(mineUser.getId());

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
