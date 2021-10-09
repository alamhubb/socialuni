package com.socialuni.social.sdk.domain.login;

import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.manage.TokenManage;
import com.socialuni.social.sdk.manage.phone.AuthenticationManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SocialPhoneLoginDomain {
    @Resource
    TokenManage tokenManage;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    AuthenticationManage authenticationManage;
    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;

    @Transactional
    public SocialLoginRO<SocialMineUserDetailRO> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        UserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        SocialMineUserDetailRO userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        TokenDO socialUserTokenDO = tokenManage.create(mineUser.getId());

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
