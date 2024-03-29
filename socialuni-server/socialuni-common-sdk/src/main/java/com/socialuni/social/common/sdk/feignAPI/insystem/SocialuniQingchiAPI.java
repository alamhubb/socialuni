/*
package com.socialuni.sdk.feignAPI.insystem;

import com.socialuni.sdk.model.RO.devAccount.DevAccountRO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

*/
/**
 * @author qinkaiyuan
 * @date 2021-07-28 11:09
 * 前端初始化内容
 *//*

@RequestMapping("socialuni/qingchi")
@FeignClient(name = "qingchi", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/circle")
public interface SocialuniQingchiAPI {
    @PostMapping("providerLogin")
    ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData);

    //仅供清池app使用，绑定手机号，绑定微信手机号，发送验证码，手机号登录都是清池专属
    @PostMapping("bindWxPhoneNum")
    ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO);

    @PostMapping("sendAuthCode")
    ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO);

    @PostMapping("bindPhoneNum")
    ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO);

    @PostMapping("phoneLogin")
    ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO);

    @PostMapping("queryDevAccount")
    ResultRO<DevAccountRO> queryDevAccount(@RequestBody @Valid OAuthUserInfoQO devAccountQueryQO);
}
*/
