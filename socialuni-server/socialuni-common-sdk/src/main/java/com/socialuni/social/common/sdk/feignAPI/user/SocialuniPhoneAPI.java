package com.socialuni.social.common.sdk.feignAPI.user;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author qinkaiyuan
 * @date 2022-01-22 11:09
 * 前端初始化内容
 */
//@RequestMapping("socialuni/phone")
@FeignClient(name = "login", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/phone")
@Tag(name = "用户模块/手机号模块")
public interface SocialuniPhoneAPI {
    @PostMapping("sendAuthCode")
    ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO);

    @PostMapping("bindWxPhoneNum")
    ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO);

    @PostMapping("bindPhoneNum")
    ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumAuthCodeQO phoneNumQO);

    @PostMapping("checkRegistry")
    ResultRO<Boolean> checkRegistry(@RequestBody @Valid @NotNull SocialSendAuthCodeQO authCodeQO);
}
