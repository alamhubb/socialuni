package com.socialuni.center.web.feignAPI;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.QO.user.OAuthUserInfoQO;
import com.socialuni.center.web.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.center.web.model.RO.SocialOAuthUserRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("oAuth")
@FeignClient(name = "oAuth", url = "${socialuni.server-url:https://api.socialuni.cn}")
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