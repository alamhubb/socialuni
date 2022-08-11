package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

//目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
@RequestMapping("login")
@FeignClient(name = "login", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniLoginAPI<T extends CenterMineUserDetailRO> {

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("providerLogin")
    ResultRO<SocialLoginRO<T>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData);

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("socialuniPhoneLogin")
    ResultRO<SocialLoginRO<T>> socialuniPhoneLogin(@RequestBody @Valid SocialProviderLoginQO loginData);
}