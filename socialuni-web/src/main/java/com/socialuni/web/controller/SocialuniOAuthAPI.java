package com.socialuni.web.controller;

import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.api.model.ResultRO;
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
    ResultRO<SocialLoginRO<CenterUserDetailRO>> oAuthUserInfo(OAuthUserInfoQO authVO);


    @PostMapping("oAuthUserPhoneNum")
    ResultRO<SocialLoginRO<CenterUserDetailRO>> oAuthUserPhoneNum(OAuthUserInfoQO authVO);

    @PostMapping("getUserInfo")
    ResultRO<SocialLoginRO<CenterUserDetailRO>> getUserInfo(OAuthUserInfoQO authVO);

    @PostMapping("getUserPhoneNum")
    ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum();*/


   /* @PostMapping("applyOAuthUserPhoneNum")
    ResultRO<OAuthUserPhoneNumRO> applyOAuthUserPhoneNum(ApplyOAuthUserInfoQO authVO);



    @PostMapping("getOAuthUserPhoneNum")
    ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum(GetOAuthUserInfoQO authVO);*/
}