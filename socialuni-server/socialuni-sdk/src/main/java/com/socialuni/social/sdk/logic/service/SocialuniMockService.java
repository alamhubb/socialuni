//package com.socialuni.social.sdk.logic.service;
//
//import com.socialuni.social.common.api.model.ResultRO;
//import com.socialuni.social.common.sdk.constant.AuthType;
//import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
//import com.socialuni.social.sdk.logic.entity.SocialuniDevAccountEntity;
//import com.socialuni.social.user.sdk.model.RO.SocialOAuthUserRO;
//import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
//import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
//import com.socialuni.social.tance.sdk.model.DevAccountModel;
//import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//
//@Service
//public class SocialuniMockService {
//    @Resource
//    SocialuniOAuthService centerOAuthService;
//    @Resource
//    SocialuniDevAccountEntity centerDevAccountEntity;
//    @Resource
//    SocialUserPhoneRedis socialUserPhoneRedis;
//    //默认会根据微信的appid去生成不太对，应该用咱们系统的appid
//    //为什么需要appid和渠道类型，因为需要去判断获取对应的开发者账户
//
//    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo() {
//
//        ResultRO<SocialLoginRO<SocialOAuthUserRO>> applySocialUniOAuthRO = mockOAuthUserInfo(AuthType.user);
//
//        return applySocialUniOAuthRO;
//    }
//
//    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum() {
//
//        ResultRO<SocialLoginRO<SocialOAuthUserRO>> applySocialUniOAuthRO = mockOAuthUserInfo(AuthType.phone);
//
//        return applySocialUniOAuthRO;
//    }
//
//    private ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo(String authType) {
////        DevAccountProviderDO devAccountProviderDO = DevAccountUtils.getDevAccountProviderDO(mpType);
////        OAuthUserInfoQO authVO = new OAuthUserInfoQO(devAccountProviderDO.getAppId(), devAccountProviderDO.getMpType());
//        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
//        SocialuniUserDo userDO = centerDevAccountEntity.getOrCreateDevAccountUserDO(devAccountModel);
//        return centerOAuthService.oAuthUserInfo(devAccountModel, userDO, authType);
//    }
//
//}