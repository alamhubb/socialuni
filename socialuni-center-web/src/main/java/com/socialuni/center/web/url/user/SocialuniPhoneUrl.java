package com.socialuni.center.web.url.user;


import com.socialuni.center.web.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.center.web.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.phone.SocialSendAuthCodeQO;
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
