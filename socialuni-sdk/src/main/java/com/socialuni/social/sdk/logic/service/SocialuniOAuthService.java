package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.dao.DO.dev.ThirdUserAuthDO;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserAuthRepository;
import com.socialuni.social.sdk.logic.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniOAuthUserROFactory;
import com.socialuni.social.sdk.logic.manage.DevAccountManage;
import com.socialuni.social.user.sdk.model.QO.OAuthUserInfoQO;
import com.socialuni.social.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
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
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        Integer devId = DevAccountFacade.getDevIdNotNull();
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
        SocialuniUserDo userDO = SocialuniUserUtil.getMineUserNotNull();
        DevAccountModel devAccountModel = devAccountManage.checkApplyAuthQO(authVO);
        return oAuthUserInfo(devAccountModel, userDO, authType);
    }

    //授权获取用户信息，根据appId知道授权给哪个商户的
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(DevAccountModel devAccountModel, SocialuniUserDo mineUser, String authType) {


        SocialLoginRO<SocialuniMineUserDetailRO> loginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, authType, devAccountModel);

        SocialOAuthUserRO socialOAuthUserRO = SocialuniOAuthUserROFactory.getSocialOAuthUserRO(loginRO.getUser());

        SocialLoginRO<SocialOAuthUserRO> loginRO1 = new SocialLoginRO<>(loginRO.getToken(), socialOAuthUserRO);

        return ResultRO.success(loginRO1);
    }
}
