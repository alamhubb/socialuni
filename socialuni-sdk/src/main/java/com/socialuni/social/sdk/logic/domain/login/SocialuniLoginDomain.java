package com.socialuni.social.sdk.logic.domain.login;

import com.socialuni.social.sdk.logic.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.logic.entity.user.SocialProviderLoginEntity;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.manage.TokenManage;
import com.socialuni.social.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.user.sdk.entity.SocialTokenDO;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialuniLoginDomain {
    @Resource
    TokenManage tokenManage;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;

    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;

    @Transactional
    public SocialLoginRO<SocialuniMineUserDetailRO> providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        SocialuniUserDo mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        return getSocialLoginROByMineUser(mineUser);
    }

    @Transactional
    public SocialLoginRO<SocialuniMineUserDetailRO> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        return getSocialLoginROByMineUser(mineUser);
    }

    public SocialLoginRO<SocialuniMineUserDetailRO> getSocialLoginROByMineUser(SocialuniUserDo mineUser) {
        SocialuniMineUserDetailRO userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser, true);

        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
