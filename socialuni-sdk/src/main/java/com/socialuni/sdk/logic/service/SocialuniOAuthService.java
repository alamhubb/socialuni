package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.constant.AuthType;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniOAuthUserROFactory;
import com.socialuni.sdk.logic.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.sdk.logic.manage.DevAccountManage;
import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.dao.DO.dev.ThirdUserAuthDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.dao.repository.dev.ThirdUserAuthRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class SocialuniOAuthService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private DevAccountManage devAccountManage;

    @Resource
    private ThirdUserAuthRepository thirdUserAuthRepository;

    public ResultRO<OAuthGetUserPhoneNumRO> getUserPhoneNum() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        Integer devId = DevAccountUtils.getDevIdNotNull();
        ThirdUserAuthDO thirdUserAuthDO = thirdUserAuthRepository.findByDevIdAndUserIdAndAuthTypeAndStatus(devId, mineUser.getUnionId(), AuthType.phone, CommonStatus.enable);
        if (thirdUserAuthDO == null) {
            return new ResultRO<>();
        }
        String phoneNum = SocialuniUserUtil.getUserPhoneNum(mineUser.getUnionId());
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
        SocialuniUserDO userDO = SocialuniUserUtil.getMineUserNotNull();
        DevAccountDO devAccountDO = devAccountManage.checkApplyAuthQO(authVO);
        return oAuthUserInfo(devAccountDO, userDO, authType);
    }

    //授权获取用户信息，根据appId知道授权给哪个商户的
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(DevAccountDO devAccountDO, SocialuniUserDO mineUser, String authType) {


        SocialLoginRO<SocialuniMineUserDetailRO> loginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, authType, devAccountDO);

        SocialOAuthUserRO socialOAuthUserRO = SocialuniOAuthUserROFactory.getSocialOAuthUserRO(loginRO.getUser());

        SocialLoginRO<SocialOAuthUserRO> loginRO1 = new SocialLoginRO<>(loginRO.getToken(), socialOAuthUserRO);

        return ResultRO.success(loginRO1);
    }
}
