package com.socialuni.social.controller;

import com.socialuni.social.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.social.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("oAuth")
public interface SocialuniOAuthAPI {
    @PostMapping("oAuthGetUserPhoneNum")
    ResultRO<OAuthGetUserPhoneNumRO> oAuthGetUserPhoneNum();

    @PostMapping("oAuthUserInfoAndPhoneNum")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfoAndPhoneNum(@RequestBody @Valid OAuthUserInfoQO authVO);


/*    @PostMapping("oAuthUserInfo")
    ResultRO<SocialLoginRO<SocialuniUserDetailRO>> oAuthUserInfo(OAuthUserInfoQO authVO);


    @PostMapping("oAuthUserPhoneNum")
    ResultRO<SocialLoginRO<SocialuniUserDetailRO>> oAuthUserPhoneNum(OAuthUserInfoQO authVO);

    @PostMapping("getUserInfo")
    ResultRO<SocialLoginRO<SocialuniUserDetailRO>> getUserInfo(OAuthUserInfoQO authVO);

    @PostMapping("getUserPhoneNum")
    ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum();*/


   /* @PostMapping("applyOAuthUserPhoneNum")
    ResultRO<OAuthUserPhoneNumRO> applyOAuthUserPhoneNum(ApplyOAuthUserInfoQO authVO);



    @PostMapping("getOAuthUserPhoneNum")
    ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum(GetOAuthUserInfoQO authVO);*/
}