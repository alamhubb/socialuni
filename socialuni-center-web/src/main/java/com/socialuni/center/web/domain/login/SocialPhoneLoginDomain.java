package com.socialuni.center.web.domain.login;

import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.center.web.manage.TokenManage;
import com.socialuni.center.web.store.SocialUserDeviceLoginHistoryStore;
import com.socialuni.center.web.model.DO.user.SocialTokenDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.user.SocialPhoneNumQO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
import com.socialuni.center.web.entity.user.SocialPhoneLoginEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialPhoneLoginDomain {
    @Resource
    TokenManage tokenManage;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    SocialUserDeviceLoginHistoryStore socialUserDeviceLoginHistoryStore;

    @Transactional
    public SocialLoginRO<SocialMineUserDetailRO> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialUserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        SocialMineUserDetailRO userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

//        SocialUserDeviceLoginHistoryDO socialUserDeviceLoginHistoryDO = new SocialUserDeviceLoginHistoryDO();

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
