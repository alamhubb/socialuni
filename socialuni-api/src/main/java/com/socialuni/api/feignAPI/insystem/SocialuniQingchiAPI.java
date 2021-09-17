package com.socialuni.api.feignAPI.insystem;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2021-07-28 11:09
 * 前端初始化内容
 */
@RequestMapping("qingchi")
@FeignClient(name = "qingchi", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniQingchiAPI {
    @PostMapping("providerLogin")
    ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData);

    //仅供清池app使用，绑定手机号，绑定微信手机号，发送验证码，手机号登录都是清池专属
    @PostMapping("bindWxPhoneNum")
    ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO);

    @PostMapping("sendAuthCode")
    ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO);

    @PostMapping("bindPhoneNum")
    ResultRO<CenterMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO);

    @PostMapping("phoneLogin")
    ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO);

    @PostMapping("queryDevAccount")
    ResultRO<SocialLoginRO<CenterMineUserDetailRO>> queryDevAccount(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO);
}
