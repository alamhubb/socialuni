package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.entity.CenterDevAccountEntity;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.store.SocialUserPhoneStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CenterMockService {
    @Resource
    OAuthService oAuthService;
    @Resource
    CenterDevAccountEntity centerDevAccountEntity;
    @Resource
    SocialUserPhoneStore socialUserPhoneStore;
    //默认会根据微信的appid去生成不太对，应该用咱们系统的appid
    //为什么需要appid和渠道类型，因为需要去判断获取对应的开发者账户

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> mockOAuthUserInfo() {

        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> applySocialUniOAuthRO = mockOAuthUserInfo(AuthType.user);

        return applySocialUniOAuthRO;
    }

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> mockOAuthUserPhoneNum(String mpType) {

        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> applySocialUniOAuthRO = mockOAuthUserInfo(AuthType.phone);

        return applySocialUniOAuthRO;
    }

    private ResultRO<SocialLoginRO<CenterMineUserDetailRO>> mockOAuthUserInfo(String authType) {
//        DevAccountProviderDO devAccountProviderDO = DevAccountUtils.getDevAccountProviderDO(mpType);
//        OAuthUserInfoQO authVO = new OAuthUserInfoQO(devAccountProviderDO.getAppId(), devAccountProviderDO.getMpType());

        UserDO userDO = centerDevAccountEntity.getOrCreateDevAccountUserDO();

        return oAuthService.oAuthUserInfo(null, userDO, authType);
    }

}