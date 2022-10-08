package com.socialuni.social.sdk.logic.domain.thirdUser;

import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.entity.AuthThirdUserEntity;
import com.socialuni.social.sdk.logic.manage.ThirdUserTokenManage;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.dao.DO.dev.ThirdUserTokenDO;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
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
    public SocialLoginRO<SocialuniMineUserDetailRO> thirdUserAuthLogin(SocialuniUserDO mineUser, String authType, DevAccountModel devAccountModel) {
        //获取开发者对应的账号
        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialuniMineUserDetailRO centerUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, authType, devAccountModel, socialMineUserDetailRO);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerUserDetailRO.getId().toString(), devAccountModel.getId(), mineUser.getUnionId());

        //生成返回对象
        SocialLoginRO<SocialuniMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return applySocialUniOAuthRO;
    }

}
