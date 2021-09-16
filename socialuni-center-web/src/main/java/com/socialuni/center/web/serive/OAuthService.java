package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.sdk.mode.DevAccountProviderDO;
import com.socialuni.center.sdk.repository.DevAccountProviderRepository;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.factory.RO.user.SocialOAuthUserROFactory;
import com.socialuni.center.web.manage.DevAccountManage;
import com.socialuni.center.web.model.DO.ThirdUserAuthDO;
import com.socialuni.center.web.repository.ThirdUserAuthRepository;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.sdk.mode.DevAccountDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.user.OAuthUserInfoQO;
import com.socialuni.social.model.model.RO.OAuthUserPhoneNumRO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class OAuthService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private DevAccountManage devAccountManage;

    @Resource
    private ThirdUserAuthRepository thirdUserAuthRepository;

    public ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum() {
        UserDO mineUser = CenterUserUtil.getMineUser();
        Integer devId = DevAccountUtils.getDevId();
        ThirdUserAuthDO thirdUserAuthDO = thirdUserAuthRepository.findByDevIdAndUserIdAndAuthTypeAndStatus(devId, mineUser.getId(), AuthType.phone, CommonStatus.enable);
        if (thirdUserAuthDO == null) {
            throw new SocialBusinessException("用户未授权");
        }
        String phoneNum = SocialUserUtil.getUserPhoneNum(mineUser.getId());
        return ResultRO.success(new OAuthUserPhoneNumRO(phoneNum));
    }

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(OAuthUserInfoQO authVO) {
        return this.oAuthUserInfo(authVO, AuthType.user);
    }

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserPhoneNum(OAuthUserInfoQO authVO) {
        return this.oAuthUserInfo(authVO, AuthType.phone);
    }

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(OAuthUserInfoQO authVO, String authType) {
        //获取devaccount
        //获取绑定的手机号，根据手机号，获取user。然后返回这个user的信息，并且返回对应这个应用的userid
        UserDO userDO = CenterUserUtil.getMineUser();
        DevAccountDO devAccountDO = devAccountManage.checkApplyAuthQO(authVO);
        return oAuthUserInfo(devAccountDO, userDO, authType);
    }

    //授权获取用户信息，根据appId知道授权给哪个商户的
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfo(DevAccountDO devAccountDO, UserDO mineUser, String authType) {
        //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<CenterMineUserDetailRO> loginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, authType, devAccountDO, socialMineUserDetailRO);

        SocialOAuthUserRO socialOAuthUserRO = SocialOAuthUserROFactory.getSocialOAuthUserRO(loginRO.getUser(), AuthType.phone.equals(authType));

        SocialLoginRO<SocialOAuthUserRO> loginRO1 = new SocialLoginRO<>(loginRO.getToken(), socialOAuthUserRO);

        return ResultRO.success(loginRO1);
    }
}
