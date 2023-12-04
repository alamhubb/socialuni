package com.socialuni.social.sdk.feignAPI.user;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
 */
//@RestController
//@RequestMapping("socialuni/login")
@FeignClient(name = "login", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/login")
@Tag(name = "用户模块/登录模块")
public interface SocialuniLoginAPI {
    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("providerLogin")
    ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData);

    @PostMapping("phoneLogin")
    ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO);

}
