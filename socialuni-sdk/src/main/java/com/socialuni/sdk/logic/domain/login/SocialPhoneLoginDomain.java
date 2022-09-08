package com.socialuni.sdk.logic.domain.login;

import com.socialuni.sdk.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.manage.TokenManage;
import com.socialuni.sdk.dao.store.SocialUserDeviceLoginHistoryStore;
import com.socialuni.sdk.dao.DO.user.SocialTokenDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.logic.entity.user.SocialPhoneLoginEntity;
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
    public SocialLoginRO<SocialuniMineUserDetailRO> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialuniUserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        SocialuniMineUserDetailRO userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

//        SocialUserDeviceLoginHistoryDO socialUserDeviceLoginHistoryDO = new SocialUserDeviceLoginHistoryDO();

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
