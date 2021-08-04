package com.socialuni.sdk.url.user;


import com.socialuni.sdk.model.QO.phone.SocialBindWxPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("phone")
public interface SocialuniPhoneUrl {
    @PostMapping("sendAuthCode")
    ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO);

    @PostMapping("bindPhoneNum")
    ResultRO<SocialMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO);

    @PostMapping("bindWxPhoneNum")
    ResultRO<SocialMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO socialBindWxPhoneNumQO);
}
