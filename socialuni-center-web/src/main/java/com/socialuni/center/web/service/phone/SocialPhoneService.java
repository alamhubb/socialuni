package com.socialuni.center.web.service.phone;


import com.socialuni.center.web.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.center.web.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.center.web.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.phone.SocialSendAuthCodeQO;
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
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<SocialMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        //微信绑定手机号方法
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }
}
