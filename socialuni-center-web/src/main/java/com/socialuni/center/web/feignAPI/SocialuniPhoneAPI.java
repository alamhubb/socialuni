package com.socialuni.center.web.feignAPI;

import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.QO.user.SocialPhoneNumQO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
import com.socialuni.center.web.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2022-01-22 11:09
 * 前端初始化内容
 */
@RequestMapping("phone")
@FeignClient(name = "phone", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniPhoneAPI {
    @PostMapping("sendAuthCode")
    ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO);

    @PostMapping("phoneLogin")
    ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO);
}
