package com.socialuni.sdk.feignAPI.user;

import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.social.common.model.ResultRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2022-01-22 11:09
 * 前端初始化内容
 */
@RequestMapping("socialuni/phone")
@Tag(name = "用户模块/手机号模块")
public interface SocialuniPhoneAPI {
    @PostMapping("sendAuthCode")
    ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO);

    @PostMapping("bindWxPhoneNum")
    ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO);

    @PostMapping("bindPhoneNum")
    ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO);
}
