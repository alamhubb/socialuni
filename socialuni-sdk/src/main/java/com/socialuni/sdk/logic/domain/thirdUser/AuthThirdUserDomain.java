package com.socialuni.sdk.logic.domain.thirdUser;

import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.entity.AuthThirdUserEntity;
import com.socialuni.sdk.logic.manage.ThirdUserTokenManage;
import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.dao.DO.dev.ThirdUserTokenDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
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
    public SocialLoginRO<SocialuniMineUserDetailRO> thirdUserAuthLogin(SocialuniUserDO mineUser, String authType, DevAccountDO devAccountDO) {
        //获取开发者对应的账号
        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialuniMineUserDetailRO centerUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, authType, devAccountDO, socialMineUserDetailRO);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerUserDetailRO.getId().toString(), devAccountDO.getId(), mineUser.getUnionId());

        //生成返回对象
        SocialLoginRO<SocialuniMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return applySocialUniOAuthRO;
    }

}
