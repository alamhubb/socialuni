package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.social.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Slf4j
@Service
public class CenterPhoneService {
    @Resource
    private SocailSendAuthCodeDomain socailSendAuthCodeDomain;
    @Resource
    private SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;

    @Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        UserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);
        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.phone, devAccountDO);
        return ResultRO.success(centerLoginRO);
    }

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        UserDO mineUser = CenterUserUtil.getMineUserNotNull();
        //校验逻辑应该拿到 domain里，因为限制了只有清池可以访问，所以不再限制ip
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }
}
