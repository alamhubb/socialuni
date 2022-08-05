package com.socialuni.center.web.serive;

import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.model.DO.dev.DevAccountDO;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.factory.RO.user.SocialOAuthUserROFactory;
import com.socialuni.center.web.manage.DevAccountManage;
import com.socialuni.center.web.model.DO.dev.ThirdUserAuthDO;
import com.socialuni.center.web.repository.dev.ThirdUserAuthRepository;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.user.OAuthUserInfoQO;
import com.socialuni.center.web.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.center.web.model.RO.SocialOAuthUserRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
import com.socialuni.center.web.constant.AuthType;
import com.socialuni.center.web.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class CenterOAuthService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private DevAccountManage devAccountManage;

    @Resource
    private ThirdUserAuthRepository thirdUserAuthRepository;

    public ResultRO<OAuthGetUserPhoneNumRO> getUserPhoneNum() {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        Integer devId = DevAccountUtils.getDevIdNotNull();
        ThirdUserAuthDO thirdUserAuthDO = thirdUserAuthRepository.findByDevIdAndUserIdAndAuthTypeAndStatus(devId, mineUser.getId(), AuthType.phone, CommonStatus.enable);
        if (thirdUserAuthDO == null) {
            return new ResultRO<>();
        }
        String phoneNum = SocialUserUtil.getUserPhoneNum(mineUser.getId());
        return ResultRO.success(new OAuthGetUserPhoneNumRO(phoneNum));
    }

/*
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(OAuthUserInfoQO authVO) {
        return this.oAuthUserInfo(authVO, AuthType.user);
    }
*/

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfoAndPhoneNum(OAuthUserInfoQO authVO) {
        return this.oAuthUserInfo(authVO, AuthType.phone);
    }

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(OAuthUserInfoQO authVO, String authType) {
        //获取devaccount
        //获取绑定的手机号，根据手机号，获取user。然后返回这个user的信息，并且返回对应这个应用的userid
        SocialUserDO userDO = CenterUserUtil.getMineUserNotNull();
        DevAccountDO devAccountDO = devAccountManage.checkApplyAuthQO(authVO);
        return oAuthUserInfo(devAccountDO, userDO, authType);
    }

    //授权获取用户信息，根据appId知道授权给哪个商户的
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(DevAccountDO devAccountDO, SocialUserDO mineUser, String authType) {


        SocialLoginRO<CenterMineUserDetailRO> loginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, authType, devAccountDO);

        SocialOAuthUserRO socialOAuthUserRO = SocialOAuthUserROFactory.getSocialOAuthUserRO(loginRO.getUser());

        SocialLoginRO<SocialOAuthUserRO> loginRO1 = new SocialLoginRO<>(loginRO.getToken(), socialOAuthUserRO);

        return ResultRO.success(loginRO1);
    }
}
