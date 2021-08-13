package com.socialuni.admin.web.service;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.social.sdk.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.social.sdk.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.social.sdk.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminPhoneService {
    @Resource
    SocialBindWxPhoneNumDomain socialBindWxPhoneNumDomain;
    @Resource
    SocialBindPhoneNumDomain socialBindPhoneNumDomain;
    @Resource
    SocailSendAuthCodeDomain socailSendAuthCodeDomain;

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

}
