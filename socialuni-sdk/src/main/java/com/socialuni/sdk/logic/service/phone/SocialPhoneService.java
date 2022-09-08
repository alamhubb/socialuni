package com.socialuni.sdk.logic.service.phone;


import com.socialuni.sdk.logic.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.sdk.logic.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.sdk.logic.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialPhoneService {
    @Resource
    SocialBindWxPhoneNumDomain socialBindWxPhoneNumDomain;
    @Resource
    SocialBindPhoneNumDomain socialBindPhoneNumDomain;
    @Resource
    SocailSendAuthCodeDomain socailSendAuthCodeDomain;

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<SocialMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        //微信绑定手机号方法
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }
}
