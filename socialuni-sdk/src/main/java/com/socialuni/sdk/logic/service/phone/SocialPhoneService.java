package com.socialuni.sdk.logic.service.phone;


import com.socialuni.sdk.logic.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.sdk.logic.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.sdk.logic.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
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
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<SocialMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        //微信绑定手机号方法
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }
}
