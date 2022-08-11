package com.socialuni.sdk.serive;

import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.sdk.factory.RO.user.SocialOAuthUserROFactory;
import com.socialuni.sdk.manage.DevAccountManage;
import com.socialuni.sdk.model.DO.dev.ThirdUserAuthDO;
import com.socialuni.sdk.repository.dev.ThirdUserAuthRepository;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.constant.AuthType;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
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
        ThirdUserAuthDO thirdUserAuthDO = thirdUserAuthRepository.findByDevIdAndUserIdAndAuthTypeAndStatus(devId, mineUser.getUnionId(), AuthType.phone, CommonStatus.enable);
        if (thirdUserAuthDO == null) {
            return new ResultRO<>();
        }
        String phoneNum = SocialUserUtil.getUserPhoneNum(mineUser.getUnionId());
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