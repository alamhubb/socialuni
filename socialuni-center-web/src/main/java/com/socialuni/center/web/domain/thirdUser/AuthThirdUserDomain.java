package com.socialuni.center.web.domain.thirdUser;

import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.model.DO.dev.DevAccountDO;
import com.socialuni.center.web.entity.AuthThirdUserEntity;
import com.socialuni.center.web.manage.ThirdUserTokenManage;
import com.socialuni.center.web.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
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
    public SocialLoginRO<CenterMineUserDetailRO> thirdUserAuthLogin(UserDO mineUser, String authType, DevAccountDO devAccountDO) {
        //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        CenterMineUserDetailRO centerUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, authType, devAccountDO, socialMineUserDetailRO);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerUserDetailRO.getId().toString(), devAccountDO.getId(), mineUser.getId());

        //生成返回对象
        SocialLoginRO<CenterMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return applySocialUniOAuthRO;
    }

}
