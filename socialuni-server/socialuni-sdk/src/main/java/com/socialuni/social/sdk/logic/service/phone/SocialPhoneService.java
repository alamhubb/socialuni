package com.socialuni.social.sdk.logic.service.phone;


import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.user.sdk.logic.domain.SocailSendAuthCodeDomain;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        String phoneNum = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        return new ResultRO<>(SocialuniMineUserDetailROFactory.getMineUserDetail());
    }

    public ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        //微信绑定手机号方法
        String phoneNum  = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(SocialuniMineUserDetailROFactory.getMineUserDetail());
    }
}
