package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.logic.entity.SocialuniDevAccountEntity;
import com.socialuni.social.tance.util.DevAccountUtils;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.common.model.ResultRO;
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
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountNotNull();
        SocialuniUserDO userDO = centerDevAccountEntity.getOrCreateDevAccountUserDO(devAccountModel);
        return centerOAuthService.oAuthUserInfo(devAccountModel, userDO, authType);
    }

}