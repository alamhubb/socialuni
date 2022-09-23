package com.socialuni.social.sdk.logic.service.phone;


import com.socialuni.social.sdk.logic.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
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
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        //微信绑定手机号方法
        SocialuniMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }
}
