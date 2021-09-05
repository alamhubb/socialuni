package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.OAuthUserInfoQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class OAuthService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> oAuthUserInfo(OAuthUserInfoQO authVO) {
        return this.oAuthUserInfo(authVO, AuthType.user);
    }

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> oAuthUserPhoneNum(OAuthUserInfoQO authVO) {
        return this.oAuthUserInfo(authVO, AuthType.phone);
    }

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> oAuthUserInfo(OAuthUserInfoQO authVO, String authType) {
        //获取devaccount
        //获取绑定的手机号，根据手机号，获取user。然后返回这个user的信息，并且返回对应这个应用的userid
        UserDO userDO = CenterUserUtil.getMineUser();

        return oAuthUserInfo(authVO, userDO, authType);
    }

    //授权获取用户信息，根据appId知道授权给哪个商户的
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> oAuthUserInfo(OAuthUserInfoQO authVO, UserDO mineUser, String authType) {
        //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        SocialLoginRO<CenterMineUserDetailRO> loginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.user, devAccountDO, socialMineUserDetailRO);

        return ResultRO.success(loginRO);
    }
}
