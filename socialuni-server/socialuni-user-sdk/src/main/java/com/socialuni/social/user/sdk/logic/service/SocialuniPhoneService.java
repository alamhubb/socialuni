package com.socialuni.social.user.sdk.logic.service;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.logic.domain.SocailSendAuthCodeDomain;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SocialuniPhoneService {
    @Resource
    private SocailSendAuthCodeDomain socailSendAuthCodeDomain;
    /*@Resource
    private AuthThirdUserDomain authThirdUserDomain;

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);
        //中心授权
        SocialLoginRO<SocialuniMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.phone, devAccountModel);
        return ResultRO.success(centerLoginRO);
    }*/

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        //校验逻辑应该拿到 domain里，因为限制了只有清池可以访问，所以不再限制ip
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }
}
