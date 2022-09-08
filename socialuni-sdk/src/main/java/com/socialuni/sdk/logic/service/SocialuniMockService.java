package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.logic.entity.SocialuniDevAccountEntity;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.constant.AuthType;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SocialuniMockService {
    @Resource
    SocialuniOAuthService centerOAuthService;
    @Resource
    SocialuniDevAccountEntity centerDevAccountEntity;
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    //默认会根据微信的appid去生成不太对，应该用咱们系统的appid
    //为什么需要appid和渠道类型，因为需要去判断获取对应的开发者账户

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo() {

        ResultRO<SocialLoginRO<SocialOAuthUserRO>> applySocialUniOAuthRO = mockOAuthUserInfo(AuthType.user);

        return applySocialUniOAuthRO;
    }

    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum() {

        ResultRO<SocialLoginRO<SocialOAuthUserRO>> applySocialUniOAuthRO = mockOAuthUserInfo(AuthType.phone);

        return applySocialUniOAuthRO;
    }

    private ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo(String authType) {
//        DevAccountProviderDO devAccountProviderDO = DevAccountUtils.getDevAccountProviderDO(mpType);
//        OAuthUserInfoQO authVO = new OAuthUserInfoQO(devAccountProviderDO.getAppId(), devAccountProviderDO.getMpType());
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        SocialuniUserDO userDO = centerDevAccountEntity.getOrCreateDevAccountUserDO(devAccountDO);
        return centerOAuthService.oAuthUserInfo(devAccountDO, userDO, authType);
    }

}