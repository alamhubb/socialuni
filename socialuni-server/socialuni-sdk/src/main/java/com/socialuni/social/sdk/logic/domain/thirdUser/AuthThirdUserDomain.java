package com.socialuni.social.sdk.logic.domain.thirdUser;

import com.socialuni.social.sdk.logic.entity.AuthThirdUserEntity;
import com.socialuni.social.sdk.logic.manage.ThirdUserTokenManage;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.tance.dev.model.DevAccountModel;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    public SocialLoginRO<SocialuniMineUserDetailRO> thirdUserAuthLogin(SocialuniUserDo mineUser, String authType, DevAccountModel devAccountModel) {
        //获取开发者对应的账号
//        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
//
//        SocialuniMineUserDetailRO centerUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, authType, devAccountModel, socialMineUserDetailRO);
//
//        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(centerUserDetailRO.getId().toString(), devAccountModel.getId(), mineUser.getUnionId());
//
//        //生成返回对象
//        SocialLoginRO<SocialuniMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();
//
//        applySocialUniOAuthRO.setToken(tokenDO.getToken());
//        applySocialUniOAuthRO.setUser(centerUserDetailRO);
//        return applySocialUniOAuthRO;
        return null;
    }

}
