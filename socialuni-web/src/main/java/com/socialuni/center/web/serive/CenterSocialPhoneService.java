package com.socialuni.center.web.serive;


import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.entity.AuthThirdUserEntity;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.social.sdk.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterSocialPhoneService {
    @Resource
    private SocialBindPhoneNumDomain socialBindPhoneNumDomain;
    @Resource
    private SocailSendAuthCodeDomain socailSendAuthCodeDomain;
    @Resource
    private AuthThirdUserEntity authThirdUserEntity;

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        mineUser = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        //生成一条对三方的手机号授权，并且返回手机号信息
        CenterMineUserDetailRO centerMineUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, AuthType.phone);
        return new ResultRO<>(centerMineUserDetailRO);
    }
}
