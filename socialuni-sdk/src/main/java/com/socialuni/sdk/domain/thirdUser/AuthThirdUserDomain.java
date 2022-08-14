package com.socialuni.sdk.domain.thirdUser;

import com.socialuni.sdk.entity.AuthThirdUserEntity;
import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.manage.ThirdUserTokenManage;
import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthThirdUserDomain {
    @Resource
    AuthThirdUserEntity authThirdUserEntity;
    @Resource
    ThirdUserTokenManage thirdUserTokenManage;

    //手机号和渠道登录，还有三方授权，三个地方使用
    public SocialLoginRO<CenterMineUserDetailRO> thirdUserAuthLogin(SocialUserDO mineUser, String authType, DevAccountDO devAccountDO) {
        //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        CenterMineUserDetailRO centerUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, authType, devAccountDO, socialMineUserDetailRO);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerUserDetailRO.getId().toString(), devAccountDO.getId(), mineUser.getUnionId());

        //生成返回对象
        SocialLoginRO<CenterMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return applySocialUniOAuthRO;
    }

}
