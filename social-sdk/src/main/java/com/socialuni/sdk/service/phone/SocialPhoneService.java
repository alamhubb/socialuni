package com.socialuni.sdk.service.phone;


import com.socialuni.sdk.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.sdk.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.sdk.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
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
        UserDO mineUser = SocialUserUtil.getMineUser();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<SocialMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        mineUser = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);
        //根据用户得到返回详情
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        //微信绑定手机号方法
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }
}
