package com.socialuni.social.sdk.logic.service.phone;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SocialuniPhoneBindService {
    @Resource
    SocialBindWxPhoneNumDomain socialBindWxPhoneNumDomain;
    @Resource
    SocialBindPhoneNumDomain socialBindPhoneNumDomain;

    public ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        //微信绑定手机号方法
        SocialuniMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(socialBindWxPhoneNumQO, mineUser);

        return new ResultRO<>(socialMineUserDetailRO);
    }
}
